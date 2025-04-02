package com.example.tagtaskschedule.dto;

import com.example.tagtaskschedule.entity.FocusTag;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 일정 응답 DTO
 */
@Getter
@Builder
public class ScheduleResponseDto {

    /**
     * 일정 ID
     */
    private Long id;

    /**
     * 일정 날짜
     */
    private String scheduleDate;

    /**
     * 할 일 내용
     */
    private String taskContent;

    /**
     * 집중 테마
     */
    private FocusTag focusTag;

    /**
     * 작성자 정보
     */
    private AuthorInfo author;

    /**
     * 생성 시간
     */
    private LocalDateTime createdAt;

    /**
     * 수정 시간
     */
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Long id, String scheduleDate, String taskContent, FocusTag focusTag,
                              AuthorInfo author, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.taskContent = taskContent;
        this.focusTag = focusTag;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    /**
     * 작성자 정보 내부 클래스
     */
    @Getter
    public static class AuthorInfo {
        private final Long id;
        private final String name;
        private final String email;

        public AuthorInfo(Long id, String name, String email) {
            this.id  = id;
            this.name = name;
            this.email = email;
        }
    }
}
