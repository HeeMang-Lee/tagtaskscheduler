package com.example.tagtaskschedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Schedule(String scheduleDate, String taskContent, FocusTag focusTag, String password,Author author) {
        this.scheduleDate = scheduleDate;
        this.taskContent = taskContent;
        this.focusTag = focusTag;
        this.password =password;
        this.author = author;
    }
}
