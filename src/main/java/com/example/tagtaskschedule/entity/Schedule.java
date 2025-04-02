package com.example.tagtaskschedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 일정(Schedule) 엔티티
 */
@Entity
@Table(name = "schedule")
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scheduler_date", nullable = false)
    private String scheduleDate;

    @Column(name = "task_content", columnDefinition = "TEXT", nullable = false)
    private String taskContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "focus_tag", nullable = false)
    private FocusTag focusTag;

    /**
     * 작성자 연관 관계
     */
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    /**
     * Schedule 생성자
     *
     * @param scheduleDate
     * @param taskContent
     * @param focusTag
     * @param author
     */
    public Schedule(String scheduleDate, String taskContent, FocusTag focusTag, Author author) {
        this.scheduleDate = scheduleDate;
        this.taskContent = taskContent;
        this.focusTag = focusTag;
        this.author = author;
    }

    /**
     * 일정 정보 수정
     *
     * @param scheduleDate
     * @param taskContent
     * @param focusTag
     * @param author
     */
    public void update(String scheduleDate, String taskContent, FocusTag focusTag, Author author) {
        this.scheduleDate = scheduleDate;
        this.taskContent = taskContent;
        this.focusTag = focusTag;
        this.author = author;
    }
}
