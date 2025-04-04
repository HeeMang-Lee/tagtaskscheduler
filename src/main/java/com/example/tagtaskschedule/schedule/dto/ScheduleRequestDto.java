package com.example.tagtaskschedule.schedule.dto;

import com.example.tagtaskschedule.schedule.entity.FocusTag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 일정 등록/수정 요청 DTO
 */
@Getter
public class ScheduleRequestDto {

    /**
     * 일정 날짜 (필수)
     */
    @NotBlank(message = "일정 날짜는 필수입니다.")
    private String scheduleDate;

    /**
     * 일정 내용 (필수, 공백 불가)
     */
    @NotBlank(message = "할 일 내용은 필수입니다.")
    private String taskContent;

    /**
     * 집중 테마 (필수)
     */
    @NotNull(message = "집중 테마는 필수입니다.")
    private FocusTag focusTag;

    /**
     * 작성자 ID (필수)
     */
    @NotNull(message = "작성자 ID는 필수입니다.")
    private Long authorId;

    /**
     * ScheduleRequestDto 생성자
     *
     * @param scheduleDate 일정 날짜
     * @param taskContent 일정 내용
     * @param focusTag 집중 태그
     * @param authorId 작성자 ID
     */
    public ScheduleRequestDto(String scheduleDate, String taskContent, FocusTag focusTag, Long authorId) {
        this.scheduleDate = scheduleDate;
        this.taskContent = taskContent;
        this.focusTag = focusTag;
        this.authorId = authorId;
    }
}
