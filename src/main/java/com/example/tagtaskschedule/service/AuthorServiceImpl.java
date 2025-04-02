package com.example.tagtaskschedule.service;

import com.example.tagtaskschedule.dto.AuthorRequestDto;
import com.example.tagtaskschedule.dto.AuthorResponseDto;
import com.example.tagtaskschedule.entity.Author;
import com.example.tagtaskschedule.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 작성자(Author) 관련 비즈니스 로직 구현체
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    /**
     * 작성자를 등록합니다.
     *
     * @param requestDto 작성자 등록 요청 DTO
     * @return 작성자 응답 DTO
     */
    @Override
    public AuthorResponseDto registerAuthor(AuthorRequestDto requestDto) {
        Author author = new Author(requestDto.getName(), requestDto.getEmail() );
        Author savedAuthor = authorRepository.save(author);

        return new AuthorResponseDto(
                savedAuthor.getId(),
                savedAuthor.getName(),
                savedAuthor.getEmail(),
                savedAuthor.getCreatedAt(),
                savedAuthor.getModifiedAt()
        );
    }
}
