package com.example.tagtaskschedule.controller;

import com.example.tagtaskschedule.dto.AuthorRequestDto;
import com.example.tagtaskschedule.dto.AuthorResponseDto;
import com.example.tagtaskschedule.dto.UpdatePasswordRequestDto;
import com.example.tagtaskschedule.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 작성자 단건 조회
     *
     * @param id 작성자 ID
     * @return 작성자 응답 DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthor(@PathVariable Long id) {
        AuthorResponseDto responseDto = authorService.getAuthor(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 비밀번호 수정
     *
     * @param id 작성자 ID
     * @param requestDto 비밀번호 수정 요청 DTO
     * @return 성공 응답
     */
    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody @Valid UpdatePasswordRequestDto requestDto
            ) {
        authorService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 작성자 삭제
     *
     * @param id 작성자 ID
     * @return 성공 응답
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
