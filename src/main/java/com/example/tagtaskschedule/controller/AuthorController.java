package com.example.tagtaskschedule.controller;

import com.example.tagtaskschedule.dto.AuthorRequestDto;
import com.example.tagtaskschedule.dto.AuthorResponseDto;
import com.example.tagtaskschedule.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 작성자(Author) 관련 요청을 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    /**
     * 작성자 등록 API
     *
     * @param requestDto 작성자 등록 요청 DTO
     * @return 작성자 등록 응답 DTO
     */
    @PostMapping
    public ResponseEntity<AuthorResponseDto> registerAuthor(@RequestBody @Valid AuthorRequestDto requestDto) {
        AuthorResponseDto responseDto = authorService.registerAuthor(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
