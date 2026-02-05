package com.klid.api.logs.common.dto;

public enum ThirdPartySystemType {
    CTRS("ctrs"),
    VMS("vms"),
    CTSS("ctss");

    private final String value;

    ThirdPartySystemType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
