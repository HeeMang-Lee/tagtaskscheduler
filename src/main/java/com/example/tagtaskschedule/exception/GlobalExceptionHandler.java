package com.example.tagtaskschedule.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 전역 예외 처리를 담당하는 핸들러 클래스
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 커스텀 예외 처리 핸들러
     *
     * @param exception CustomException
     * @return 응답 JSON (status, message)
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(CustomException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", exception.getStatus().value());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, exception.getStatus());
    }

    /**
     * Bean Validation (DTO 유효성 검사) 실패 처리 핸들러
     *
     * @param exception MethodArgumentNotValidException
     * @return 응답 JSON (Status, message, fieldErrors)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 400);
        body.put("message", "입력값이 유효하지 않습니다.");

        Map<String, String> fieldErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(err -> {
            fieldErrors.put(err.getField(), err.getDefaultMessage());
                });
        body.put("fieldErrors", fieldErrors);

        return ResponseEntity.badRequest().body(body);
    }
}
