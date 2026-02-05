package com.klid.api.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthPrimaryResponseDTO {
    private String code;
    private String message;
    private String otpSecretKey;

    public boolean hasOtpSecretKey() {
        return StringUtils.isNotBlank(otpSecretKey);
    }
}
