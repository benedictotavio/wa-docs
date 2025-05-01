package br.com.wa_docs.mockserver.enums;

public enum BodyFormat {
    JSON("application/json"),
    XML("application/xml"),
    TEXT("text/plain"),
    HTML("text/html"),
    FORM_DATA("multipart/form-data"),
    APPLICATION_OCTET_STREAM("application/octet-stream");

    private String value;

    BodyFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
