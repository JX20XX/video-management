package org.example.restful;

import org.springframework.http.HttpStatus;

public enum ResultCode {
    SUCCESS(HttpStatus.OK.value(), "SUCCESS"),
    PARAM_IS_INVALID(HttpStatus.BAD_REQUEST.value(), "param is invalid"),
    PARAM_IS_BLANK(HttpStatus.NO_CONTENT.value(), "param is null"),
    SERVER_INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "server internal exception occurred");

    private Integer code;
    private String message;

    private ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
