package com.Fortinet.Fortinet.Enum;

import com.fasterxml.jackson.annotation.JsonValue;

public enum enumLicencia {
    BITDEFENDER("BITDEFENDER"),
    ESET("ESET"),
    KASPERSKY("KASPERSKY"),
    SOPHOS("SOPHOS");

    private final String value;

    enumLicencia(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}