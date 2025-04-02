package com.example.tagtaskschedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 작성자 등록 응답 DTO
 */
@Getter
public class AuthorResponseDto {

    /**
     * 작성자 ID
     */
    private final Long id;

    /**
     * 작성자 이름
     */
    private final String name;

    /**
     * 작성자 이메일
     */
    private final String email;

    /**
     * 생성 시간
     */
    private final LocalDateTime createdAt;

    /**
     * 수정 시간
     */
    private final LocalDateTime modifiedAt;

    public AuthorResponseDto(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;

    }
}
