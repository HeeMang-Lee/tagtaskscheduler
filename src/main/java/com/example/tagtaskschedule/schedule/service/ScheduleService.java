package com.example.tagtaskschedule.schedule.service;

import com.example.tagtaskschedule.schedule.dto.ScheduleRequestDto;
import com.example.tagtaskschedule.schedule.dto.ScheduleResponseDto;

import java.util.List;

/**
 * 일정(Schedule) 관련 서비스 인터페이스
 */
public interface ScheduleService {

    ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto);
    ScheduleResponseDto getSchedule(Long id);
    List<ScheduleResponseDto> getSchedules(Long authorId, String focusTag);
    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto);
    void deleteSchedule(Long id);

}
