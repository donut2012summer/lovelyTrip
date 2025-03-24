package com.vic.lovelytrip.lib;

public enum HttpStatusEnum {

    // 2xx Success
    OK(200, "OK"),

    // 4xx Client Errors
    BAD_REQUEST(400, "Bad Request"),

    // 5xx Server Errors
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable");

    private int statusCode;

    private String statusMessage;

    HttpStatusEnum(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

}
