package com.example.tagtaskschedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "author")
@Getter
@NoArgsConstructor
public class Author extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Author 생성자
     *
     * @param name 이름
     * @param email 이메일
     * @param password 비밀번호
     */
    public Author(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * 비밀번호 수정
     *
     * @param newPassword 새로운 비밀번호
     */
    public void updatePassword(String newPassword) {
        this.password = password;
    }
}
