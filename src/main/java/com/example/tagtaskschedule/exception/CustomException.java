package com.example.tagtaskschedule.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 사용자 정의 예외 클래스
 */
@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return errorCode.getStatus();
    }
}
