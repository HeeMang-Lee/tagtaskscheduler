package com.example.tagtaskschedule.author.service;

import com.example.tagtaskschedule.author.dto.AuthorRequestDto;
import com.example.tagtaskschedule.author.dto.AuthorResponseDto;
import com.example.tagtaskschedule.author.entity.Author;

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
