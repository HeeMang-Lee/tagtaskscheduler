package com.example.tagtaskschedule.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

/**
 * 작성자 등록 요청 DTO
 */
@Getter
public class AuthorRequestDto {

    /**
     * 작성자 이름 (필수, 공백 불가)
     */
    @NotBlank(message = "이름은 필수입니다.")
    private final String name;

    /**
     * 작성자 이메일 (필수, 이메일 형식)
     */
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 형식이어야 합니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String password;


    /**
     * AuthorRequestDto 생성자
     *
     * @param name 이름
     * @param email 이메일
     * @param password 비밀번호
     */
    public AuthorRequestDto(String name, String email,String password) {
        this.name = name ;
        this.email = email;
        this.password = password;
    }

}
