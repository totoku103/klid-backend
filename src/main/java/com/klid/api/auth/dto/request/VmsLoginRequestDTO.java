package com.klid.api.auth.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VmsLoginRequestDTO {
    private String id;
    private String password;

    public void setId(final String id) {
        this.id = id != null ? id.trim() : null;
    }
}
