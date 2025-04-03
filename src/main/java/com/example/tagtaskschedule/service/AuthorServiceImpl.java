package com.example.tagtaskschedule.service;

import com.example.tagtaskschedule.config.PassWordEncoder;
import com.example.tagtaskschedule.dto.AuthorRequestDto;
import com.example.tagtaskschedule.dto.AuthorResponseDto;
import com.example.tagtaskschedule.entity.Author;
import com.example.tagtaskschedule.exception.CustomException;
import com.example.tagtaskschedule.exception.ErrorCode;
import com.example.tagtaskschedule.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * 작성자(Author) 관련 비즈니스 로직 구현체
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final PassWordEncoder passWordEncoder;

    /**
     * 작성자를 등록합니다.
     *
     * @param requestDto 작성자 등록 요청 DTO
     * @return 작성자 응답 DTO
     */
    @Override
    public AuthorResponseDto registerAuthor(AuthorRequestDto requestDto) {

        if (authorRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        String encodedPassword = passWordEncoder.encode(requestDto.getPassword());
        Author author = new Author(
                requestDto.getName(),
                requestDto.getEmail(),
                encodedPassword
        );
        Author savedAuthor = authorRepository.save(author);

        return new AuthorResponseDto(
                savedAuthor.getId(),
                savedAuthor.getName(),
                savedAuthor.getEmail(),
                savedAuthor.getCreatedAt(),
                savedAuthor.getModifiedAt()
        );
    }

    /**
     * 작성자 단건을 조회합니다.
     *
     * @param id 작성자 ID
     * @return 작성자 응답 DTO
     */
    @Override
    public AuthorResponseDto getAuthor(Long id) {
        Author author = authorRepository.findByIdOrElseThrow(id);

        return new AuthorResponseDto(
                author.getId(),
                author.getName(),
                author.getEmail(),
                author.getCreatedAt(),
                author.getModifiedAt()
        );
    }

    /**
     * 비밀번호를 수정합니다.
     *
     * @param id 작성자 ID
     * @param oldPassword 기존 비밀번호
     * @param newPassword 새로운 비밀번호
     */
    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        Author author = authorRepository.findByIdOrElseThrow(id);

        if (!author.getPassword().equals(oldPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        author.updatePassword(newPassword);
    }

    /**
     * 작성자를 삭제합니다.
     *
     * @param id 작성자 ID
     */
    @Override
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findByIdOrElseThrow(id);
        authorRepository.delete(author);
    }

    /**
     * 로그인 요청을 처리합니다.
     *
     * @param email 이메일 (로그인 식별자)
     * @param password 비밀번호
     * @return 로그인 성공한 Author 객체
     * @throws ResponseStatusException 로그인 실패 시 401 에러
     */
    @Override
    public Author login(String email, String password) {
        Author author = authorRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));

        if (!passWordEncoder.isPassword(password, author.getPassword())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        return author;
    }
}
