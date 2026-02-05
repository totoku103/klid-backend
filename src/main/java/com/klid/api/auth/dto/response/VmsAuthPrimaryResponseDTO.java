package com.klid.api.auth.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VmsAuthPrimaryResponseDTO {
    private String otpSecretKey;
    private String email;
    private String gpkiKey;
}
