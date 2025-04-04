package com.example.tagtaskschedule.author.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 비밀번호 수정 요청 DTO
 */
@Getter
public class UpdatePasswordRequestDto {

    /**
     * 기존 비밀번호
     */
    @NotBlank(message = "기존 비밀번호는 필수입니다.")
    private final String oldPassword;

    /**
     * 새로운 비밀번호
     */
    @NotBlank(message = "새로운 비밀번호는 필수입니다.")
    private final String newPassword;

    /**
     * UpdatePasswordRequestDto
     *
     * @param oldPassword 기존 비밀번호
     * @param newPassword 새로운 비밀번호
     */
    public UpdatePasswordRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
