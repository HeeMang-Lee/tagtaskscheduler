package com.example.tagtaskschedule.service;

import com.example.tagtaskschedule.dto.ScheduleRequestDto;
import com.example.tagtaskschedule.dto.ScheduleResponseDto;

/**
 * 일정(Schedule) 관련 서비스 인터페이스
 */
public interface ScheduleService {

    /**
     * 일정을 생성합니다.
     *
     * @param requestDto 일정 등록 요청 DTO
     * @return 일정 응답 DTO
     */
    ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto);
}
