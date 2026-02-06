package com.klid.api.logs.common.controller;

import com.klid.api.logs.common.dto.CsvDownloadReqDTO;
import com.klid.webapp.common.CustomException;
import com.klid.webapp.common.SessionManager;
import com.klid.webapp.common.file.service.CsvFileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/logs/csv")
public class LogsCsvDownloadController {

    private final CsvFileService csvFileService;

    private MenuInfo getMenuInfo(final HttpServletRequest httpServletRequest) {
        final String referer = httpServletRequest.getHeader("referer");
        final String userId = SessionManager.getUser() != null
                ? SessionManager.getUser().getUserId() : "unknown";

        if (StringUtils.isBlank(referer)) {
            log.error("[CSV_DOWNLOAD_ERROR] Referer header is missing. userId={}", userId);
            throw new CustomException("잘못된 요청입니다.");
        }

        log.debug("[CSV_DOWNLOAD_REQUEST] Resolving menu GUID from referer. referer={}, userId={}", referer, userId);
        final MenuInfo menuInfo = new MenuInfo();

        if (referer.contains("/main/logs/user-connect-log/summary.do")
                || referer.contains("/api/logs/user-connect/summary")) {
            // 사용자 접속 로그 현황(요약)
            menuInfo.setGuid("98561932-0D0E-4312-A969-15FFCD40C09C");
            menuInfo.setRefTableName("USR_LOGINFO, SITE_USER_ACCESS_HISTORY");
        } else if (referer.contains("/main/logs/user-connect-log/daily.do")
                || referer.contains("/api/logs/user-connect/daily")) {
            // 사용자 접속 로그 현황(일별)
            menuInfo.setGuid("9B82E46D-BD6C-48FC-A4B1-08AB5A612423");
            menuInfo.setRefTableName("SITE_USER_ACCESS_SUM_5MIN");
        } else if (referer.contains("/main/logs/user-connect-log/period.do")
                || referer.contains("/api/logs/user-connect/period")) {
            // 사용자 접속 로그 현황(기간별)
            menuInfo.setGuid("1F64D749-0BA6-4765-80CA-B1E050B9223F");
            menuInfo.setRefTableName("SITE_USER_ACCESS_SUM_HOUR");
        } else if (referer.contains("/main/logs/user-connect-log/institution.do")
                || referer.contains("/api/logs/user-connect/institution")) {
            // 사용자 접속 로그 현황(기관별)
            menuInfo.setGuid("ECD00E4F-2C5D-46B6-93BB-0A2156EC02AE");
            menuInfo.setRefTableName("SITE_USER_ACCESS_SUM_HOUR");
        } else if (referer.contains("/main/logs/user-action-log/summary.do")
                || referer.contains("/api/logs/user-action/summary")) {
            // 사용자 행위 로그 현황(요약)
            menuInfo.setGuid("365C0D93-B77E-43EF-90EE-CC709A9EFAEA");
            menuInfo.setRefTableName("USER_ACT_HIST, SITE_USER_ACTIVITY_HISTORY");
        } else if (referer.contains("/main/logs/user-action-log/daily.do")
                || referer.contains("/api/logs/user-action/daily")) {
            // 사용자 행위 로그 현황(일별)
            menuInfo.setGuid("0EF2C268-6946-4910-9C93-29F236B0646E");
            menuInfo.setRefTableName("SITE_USER_ACTIVITY_SUM_5MIN");
        } else if (referer.contains("/main/logs/user-action-log/period.do")
                || referer.contains("/api/logs/user-action/period")) {
            // 사용자 행위 로그 현황(기간별)
            menuInfo.setGuid("83626AED-467A-438B-9A67-2F83FB32A01A");
            menuInfo.setRefTableName("SITE_USER_ACTIVITY_SUM_HOUR");
        } else if (referer.contains("/main/logs/user-action-log/institution.do")
                || referer.contains("/api/logs/user-action/institution")) {
            // 사용자 행위 로그 현황(기관별)
            menuInfo.setGuid("545E1470-DC0F-45C8-BA94-E068859A4694");
            menuInfo.setRefTableName("SITE_USER_ACTIVITY_SUM_HOUR");
        } else {
            log.error("[CSV_DOWNLOAD_ERROR] Invalid referer URL. referer={}, userId={}", referer, userId);
            throw new CustomException("잘못된 요청입니다.");
        }

        log.debug("[CSV_DOWNLOAD_REQUEST] Menu GUID resolved. guid={}, refTable={}, userId={}",
                menuInfo.getGuid(), menuInfo.getRefTableName(), userId);
        return menuInfo;
    }

    @PostMapping(value = "/download", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void getCsvFile(final HttpServletRequest httpServletRequest,
                           final HttpServletResponse response,
                           @RequestBody final CsvDownloadReqDTO req) throws Exception {
        final long startTime = System.currentTimeMillis();
        final String sessionId = httpServletRequest.getSession().getId();

        String userId = null;
        String userName = null;
        String finalName = null;
        MenuInfo menuInfo = null;

        try {
            // 사용자 정보 먼저 조회
            userId = SessionManager.getUser() != null
                    ? SessionManager.getUser().getUserId() : "unknown";
            userName = SessionManager.getUser() != null
                    ? SessionManager.getUser().getUserName() : "unknown";

            // 입력 검증
            if (StringUtils.isBlank(req.getReason())) {
                log.warn("[CSV_DOWNLOAD_VALIDATION_FAILED] Download reason is empty. userId={}, sessionId={}",
                        userId, sessionId);
                throw new CustomException("다운로드 사유를 입력해주세요");
            }

            if (req.getReason().trim().length() > 2000) {
                log.warn("[CSV_DOWNLOAD_VALIDATION_FAILED] Download reason exceeds max length. userId={}, sessionId={}, reasonLength={}",
                        userId, sessionId, req.getReason().trim().length());
                throw new CustomException("다운로드 사유는 2000자를 넘을 수 없습니다.");
            }

            // 메뉴 정보 조회
            menuInfo = getMenuInfo(httpServletRequest);

            // 파일명 생성
            final String baseName;
            if (!StringUtils.isBlank(req.getFilename())) {
                baseName = req.getFilename().trim();
            } else {
                final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                baseName = "export_" + LocalDateTime.now().format(fmt);
            }
            finalName = baseName + ".csv";

            // CSV 다운로드 요청 로깅 (보안 감사용)
            log.info("[CSV_DOWNLOAD_START] userId={}, userName={}, filename={}, guid={}, refTable={}, sessionId={}, requestDetail={}",
                    userId, userName, finalName, menuInfo.getGuid(), menuInfo.getRefTableName(), sessionId, req.toString());

            // CSV 파일 생성 및 전송
            csvFileService.getFile(response, menuInfo.getGuid(), menuInfo.getRefTableName(),
                    userId, userName, finalName, req.getHeaders(), req.getRows(), req.getReason(), req.getSearchOptions());

            // 성공 로깅
            final long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("[CSV_DOWNLOAD_SUCCESS] userId={}, userName={}, filename={}, rowCount={}, elapsedTime={}ms, sessionId={}",
                    userId, userName, finalName,
                    (req.getRows() != null ? req.getRows().size() : 0), elapsedTime, sessionId);

        } catch (CustomException e) {
            final long elapsedTime = System.currentTimeMillis() - startTime;
            log.error("[CSV_DOWNLOAD_FAILED] CustomException occurred. userId={}, userName={}, filename={}, errorMessage={}, elapsedTime={}ms, sessionId={}",
                    userId, userName, finalName, e.getMessage(), elapsedTime, sessionId);
            throw e;

        } catch (Exception e) {
            final long elapsedTime = System.currentTimeMillis() - startTime;
            log.error("[CSV_DOWNLOAD_FAILED] Unexpected exception occurred. userId={}, userName={}, filename={}, guid={}, errorMessage={}, errorClass={}, elapsedTime={}ms, sessionId={}",
                    userId, userName, finalName,
                    (menuInfo != null ? menuInfo.getGuid() : "null"),
                    e.getMessage(), e.getClass().getName(), elapsedTime, sessionId, e);
            throw e;
        }
    }

    private static class MenuInfo {
        private String guid;
        private String refTableName;

        public String getGuid() {
            return guid;
        }

        public void setGuid(final String guid) {
            this.guid = guid;
        }

        public String getRefTableName() {
            return refTableName;
        }

        public void setRefTableName(final String refTableName) {
            this.refTableName = refTableName;
        }
    }
}
