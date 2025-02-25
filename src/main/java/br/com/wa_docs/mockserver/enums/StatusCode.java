package br.com.wa_docs.mockserver.enums;

public enum StatusCode {
    OK(200),
    CREATED(201),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501),
    BAD_GATEWAY(502),
    SERVICE_UNAVAILABLE(503),
    GATEWAY_TIMEOUT(504);

    private int value;

    StatusCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StatusCode fromValue(int value) {
        for (StatusCode statusCode : StatusCode.values()) {
            if (statusCode.getValue() == value) {
                return statusCode;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + value);
    }
}
