package com.example.tagtaskschedule.auth.controller;

import com.example.tagtaskschedule.auth.dto.LoginRequestDto;
import com.example.tagtaskschedule.author.entity.Author;
import com.example.tagtaskschedule.author.service.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증(Authentication) 관련 요청을 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthorService authorService;

    /**
     * 로그인 요청
     *
     * @param requestDto 로그인 요청 DTO (이메일, 비밀번호)
     * @param request HttpServletRequest (세션 생성에 사용)
     * @return 로그인 성공 메시지
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody @Valid LoginRequestDto requestDto,
            HttpServletRequest request
    ) {
        Author author = authorService.login(requestDto.getEmail(), requestDto.getPassword());

        HttpSession session = request.getSession(true);
        session.setAttribute("LOGIN_AUTHOR", author.getId());

        return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
    }

    /**
     * 로그아웃 요청
     *
     * @param request HttpServletRequest(세션 제거에 사용)
     * @return 로그아웃 성공 메시지
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }
}
