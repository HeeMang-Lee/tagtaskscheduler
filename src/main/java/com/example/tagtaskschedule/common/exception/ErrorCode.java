package com.example.tagtaskschedule.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 공통 예외 코드 Enum
 */
@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 입력값입니다."),
    EMAIL_FORMAT_INVALID(HttpStatus.BAD_REQUEST, "이메일 형식이 올바르지 않습니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일입니다."),
    USERNAME_TOO_LONG(HttpStatus.BAD_REQUEST, "이름은 4자 이하여야 합니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
