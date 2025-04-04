package com.example.tagtaskschedule.author.repository;

import com.example.tagtaskschedule.author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * 작성자(Author) 엔티티에 대한 데이터 접근 레포지토리
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * ID로 작성자를 조회하거나 없을 경우 예외를 발생시킵니다.
     *
     * @param id 작성자 ID
     * @return 존재하는 작성자 엔티티
     */
    default Author findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "작성자 ID가 존재하지 않습니다. id = " + id) );
    }

    /**
     * 이메일로 작성자를 조회합니다.
     *
     * @param email 이메일
     * @return Optional<Author>
     */
    Optional<Author> findByEmail(String email);
}
