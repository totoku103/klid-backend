package com.klid.api.common.policy.persistence;

import com.klid.api.common.policy.dto.PolicyDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 정책 설정 Mapper
 */
@Repository
public interface PolicyConfMapper {

    /**
     * 정책 설정 정보 조회
     *
     * @param policyKind 정책 종류
     * @param policyName 정책명
     * @return 정책 설정 목록
     */
    List<PolicyDTO> selectPolicyConfInfo(
            @Param("policyKind") String policyKind,
            @Param("policyName") String policyName
    );

    /**
     * 정책 설정 업데이트
     *
     * @param params 업데이트할 정책 설정 파라미터 맵
     * @return 업데이트된 행 수
     */
    int updatePolicyConfig(Map<String, Object> params);
}
