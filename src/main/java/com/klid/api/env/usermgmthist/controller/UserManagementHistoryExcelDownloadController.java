package com.klid.api.env.usermgmthist.controller;

import com.klid.webapp.common.CustomException;
import com.klid.webapp.common.SessionManager;
import com.klid.webapp.common.file.service.ExcelFileService;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/env/user-management/history/excel")
@RequiredArgsConstructor
@Slf4j
public class UserManagementHistoryExcelDownloadController {

    private final ExcelFileService excelFileService;

    private SimpleMenuInfo getGuid() {
        final SimpleMenuInfo simpleMenuInfo = new SimpleMenuInfo();
        simpleMenuInfo.setGuid("37EFE475-2428-49B8-95CE-2AA78262631F");
        simpleMenuInfo.setRefTableName("COMM_USER_REQUEST");
        return simpleMenuInfo;
    }

    @PostMapping(value = "/download", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void getExcelFile(HttpServletRequest httpServletRequest,
                             HttpServletResponse response,
                             @RequestBody Request requestDto) throws Exception {
        final long startTime = System.currentTimeMillis();
        final String sessionId = httpServletRequest.getSession().getId();

        String userId = null;
        String userName = null;
        String finalName = null;
        SimpleMenuInfo simpleMenuInfo = null;

        try {
            userId = SessionManager.getUser() != null ? SessionManager.getUser().getUserId() : "unknown";
            userName = SessionManager.getUser() != null ? SessionManager.getUser().getUserName() : "unknown";

            if (StringUtils.isBlank(requestDto.getReason())) {
                log.warn("[EXCEL_DOWNLOAD_VALIDATION_FAILED] Download reason is empty. userId=" + userId + ", sessionId=" + sessionId);
                throw new CustomException("다운로드 사유를 입력해주세요");
            }

            if (requestDto.getReason().trim().length() > 2000) {
                log.warn("[EXCEL_DOWNLOAD_VALIDATION_FAILED] Download reason exceeds max length. userId=" + userId + ", sessionId=" + sessionId + ", reasonLength=" + requestDto.getReason().trim().length());
                throw new CustomException("다운로드 사유는 2000자를 넘을 수 없습니다.");
            }

            if (StringUtils.isBlank(requestDto.getPassword())) {
                log.warn("[EXCEL_DOWNLOAD_VALIDATION_FAILED] Password is empty. userId=" + userId + ", sessionId=" + sessionId);
                throw new CustomException("엑셀 파일 비밀번호를 입력해주세요");
            }

            simpleMenuInfo = getGuid();

            final String baseName;
            if (!StringUtils.isBlank(requestDto.getFileName())) {
                baseName = requestDto.getFileName().trim();
            } else {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                baseName = "export_" + LocalDateTime.now().format(fmt);
            }
            finalName = baseName + ".xls";

            log.info("[EXCEL_DOWNLOAD_START] userId=" + userId
                    + ", userName=" + userName
                    + ", filename=" + finalName
                    + ", guid=" + simpleMenuInfo.getGuid()
                    + ", refTable=" + simpleMenuInfo.getRefTableName()
                    + ", sessionId=" + sessionId
                    + ", requestDetail=" + requestDto.toString());

            final SimpleMenuInfo menuInfo = getGuid();
            excelFileService.getFileWithMapHeadersAndPassword(response, menuInfo.getGuid(), menuInfo.getRefTableName(),
                    userId, userName, finalName, requestDto.getSheetName(),
                    requestDto.getGroupHeaders(), requestDto.getHeaders(), requestDto.getRows(),
                    requestDto.getReason(), requestDto.getSearchOptions(), requestDto.getPassword());

            final long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("[EXCEL_DOWNLOAD_SUCCESS] userId=" + userId
                    + ", userName=" + userName
                    + ", filename=" + finalName
                    + ", rowCount=" + (requestDto.getRows() != null ? requestDto.getRows().size() : 0)
                    + ", elapsedTime=" + elapsedTime + "ms"
                    + ", sessionId=" + sessionId);
        } catch (Exception e) {
            final long elapsedTime = System.currentTimeMillis() - startTime;
            log.error("[EXCEL_DOWNLOAD_FAILED] Unexpected exception occurred. userId=" + userId
                    + ", userName=" + userName
                    + ", filename=" + finalName
                    + ", guid=" + (simpleMenuInfo != null ? simpleMenuInfo.getGuid() : "null")
                    + ", errorMessage=" + e.getMessage()
                    + ", errorClass=" + e.getClass().getName()
                    + ", elapsedTime=" + elapsedTime + "ms"
                    + ", sessionId=" + sessionId, e);
            throw e;
        }
    }

    public static class SimpleMenuInfo {
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

    public static class Request {
        private String fileName;
        private String sheetName;
        private List<String> groupHeaders;
        private List<Map<String, String>> headers;
        private List<Map<String, Object>> rows;
        private String reason;
        private String searchOptions;
        private String password;

        public String getFileName() {
            return fileName;
        }

        public void setFileName(final String fileName) {
            this.fileName = fileName;
        }

        public String getSheetName() {
            return sheetName;
        }

        public void setSheetName(final String sheetName) {
            this.sheetName = sheetName;
        }

        public List<String> getGroupHeaders() {
            return groupHeaders;
        }

        public void setGroupHeaders(final List<String> groupHeaders) {
            this.groupHeaders = groupHeaders;
        }

        public List<Map<String, String>> getHeaders() {
            return headers;
        }

        public void setHeaders(final List<Map<String, String>> headers) {
            this.headers = headers;
        }

        public List<Map<String, Object>> getRows() {
            return rows;
        }

        public void setRows(final List<Map<String, Object>> rows) {
            this.rows = rows;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(final String reason) {
            this.reason = reason;
        }

        public String getSearchOptions() {
            return searchOptions;
        }

        public void setSearchOptions(final String searchOptions) {
            this.searchOptions = searchOptions;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(final String password) {
            this.password = password;
        }
    }
}
