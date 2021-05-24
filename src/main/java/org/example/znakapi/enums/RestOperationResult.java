package org.example.znakapi.enums;

public enum RestOperationResult {
    SUCCESS(1L, "success processing"),
    RQERROR(2L, "request parameters error");

    private final Long code;
    private final String value;

    RestOperationResult(Long code, final String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Long getCode() {
        return code;
    }
}
