package com.example.tagtaskschedule.entity;

import jakarta.persistence.*;

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
}
