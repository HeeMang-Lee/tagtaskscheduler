package com.example.tagtaskschedule.service;

import com.example.tagtaskschedule.dto.AuthorRequestDto;
import com.example.tagtaskschedule.dto.AuthorResponseDto;

/**
 * 작성자(Author) 관련 서비스 인터페이스
 */
public interface AuthorService {

    /**
     * 작성자를 등록합니다.
     *
     * @param requestDto 작성자 등록 요청 DTO
     * @return 작성자 응답 DTO
     */
    AuthorResponseDto registerAuthor(AuthorRequestDto requestDto);
}
