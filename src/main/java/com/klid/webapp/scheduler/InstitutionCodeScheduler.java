package com.klid.webapp.scheduler;


import com.klid.api.logs.common.dto.InstitutionCodeResDTO;
import com.klid.api.logs.common.dto.ThirdPartySystemType;
import com.klid.api.logs.common.service.InstitutionCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 기관 코드 정보를 주기적으로 조회하는 스케줄러
 */
@Component
@Slf4j
public class InstitutionCodeScheduler {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final InstitutionCodeService institutionCodeService;

    public InstitutionCodeScheduler(final InstitutionCodeService institutionCodeService) {
        this.institutionCodeService = institutionCodeService;
    }

    /**
     * 2시간마다 기관 코드 목록을 조회하는 스케줄러
     * cron: "초 분 시 일 월 요일"
     * 0 0 0/2 * * * : 매 2시간마다 (0시, 2시, 4시, ...)
     */
    @Scheduled(cron = "0 0 0/2 * * *")
    public void fetchInstitutionCodeList() {
        final String currentTime = LocalDateTime.now().format(formatter);
        log.info("=== 기관 코드 스케줄러 시작 - {} ===", currentTime);

        try {
            final List<InstitutionCodeResDTO> vmsCodes = institutionCodeService.getOtherInstitutionCodeList(ThirdPartySystemType.VMS);
            log.info("VMS 기관 코드 조회 완료: {}건", vmsCodes.size());

            final List<InstitutionCodeResDTO> ctssCode = institutionCodeService.getOtherInstitutionCodeList(ThirdPartySystemType.CTSS);
            log.info("CTSS 기관 코드 조회 완료: {}건", ctssCode.size());

            log.info("=== 기관 코드 스케줄러 정상 완료 ===");

        } catch (Exception e) {
            log.error("기관 코드 조회 중 오류 발생: {}", e.getMessage(), e);
        }
    }


    @Scheduled(initialDelay = 10000, fixedDelay = Long.MAX_VALUE)
    public void fetchInstitutionCodeListOnStartup() {
        log.info("=== 애플리케이션 시작 시 기관 코드 초기 조회 ===");
        fetchInstitutionCodeList();
    }
}
