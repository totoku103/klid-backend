package com.klid.api.monitoring.health.persistence;

import com.klid.api.monitoring.health.dto.HealthCheckUrlDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 헬스체크 URL Mapper
 */
@Repository
public interface HealthCheckUrlMapper {

    /**
     * 헬스체크 URL 목록 조회
     */
    List<HealthCheckUrlDTO> selectHealthCheckUrl(Map<String, Object> params);

    /**
     * 헬스체크 URL 등록
     */
    int addHealthCheckUrl(Map<String, Object> params);

    /**
     * 헬스체크 URL 수정
     */
    void editHealthCheckUrl(Map<String, Object> params);

    /**
     * 집중관리 등록
     */
    void editWatchOn(Map<String, Object> params);

    /**
     * 집중관리 해제
     */
    void editWatchOff(Map<String, Object> params);

    /**
     * 헬스체크 URL 상세 조회
     */
    HealthCheckUrlDTO selectDetailHealthCheckUrl(Map<String, Object> params);

    /**
     * 헬스체크 URL 삭제
     */
    void delHealthCheckUrl(Map<String, Object> params);

    /**
     * 헬스체크 장애이력 목록 조회
     */
    List<HealthCheckUrlDTO> selectHealthCheckHist(Map<String, Object> params);

    /**
     * 헬스체크 URL 목록 조회 (중복체크용)
     */
    List<HealthCheckUrlDTO> selectUrls(Map<String, Object> params);

    /**
     * 헬스체크 상태 통계 조회
     */
    List<Map<String, Object>> selectHealthCheckStat(Map<String, Object> params);

    /**
     * 관련 기관 코드 조회
     */
    List<Integer> selectRelateInstCd(Map<String, Object> params);

    /**
     * 헬스체크 URL 다중 등록
     */
    void addHealthCheckMultiUrl(Map<String, Object> params);

    /**
     * 마지막 시퀀스 조회
     */
    int selectLastSeq();
}
