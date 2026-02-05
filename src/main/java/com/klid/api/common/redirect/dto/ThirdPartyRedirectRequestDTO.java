package com.klid.api.common.redirect.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 타 시스템 리다이렉트 요청 DTO
 *
 * <p>암호화된 사용자 정보를 포함합니다.</p>
 */
@Data
public class ThirdPartyRedirectRequestDTO {

    @JsonProperty("encrypted_data")
    private String encryptedData;

    @JsonProperty("system_type")
    private String systemType;

    @JsonProperty("client_ip")
    private String clientIp;
}
