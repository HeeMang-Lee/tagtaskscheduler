package com.example.tagtaskschedule.service;

import com.example.tagtaskschedule.dto.AuthorRequestDto;
import com.example.tagtaskschedule.dto.AuthorResponseDto;
import com.example.tagtaskschedule.entity.Author;

/**
 * 작성자(Author) 관련 서비스 인터페이스
 */
public interface AuthorService {

    AuthorResponseDto registerAuthor(AuthorRequestDto requestDto);
    AuthorResponseDto getAuthor(Long id);
    void updatePassword(Long id, String oldPassword, String newPassword);
    void deleteAuthor(Long id);
    Author login(String email, String password);

}
