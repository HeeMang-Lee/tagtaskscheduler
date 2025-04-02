package com.example.tagtaskschedule.service;

import com.example.tagtaskschedule.dto.ScheduleRequestDto;
import com.example.tagtaskschedule.dto.ScheduleResponseDto;
import com.example.tagtaskschedule.entity.Author;
import com.example.tagtaskschedule.entity.Schedule;
import com.example.tagtaskschedule.repository.AuthorRepository;
import com.example.tagtaskschedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 일정(Schedule) 관련 비즈니스 로직 구현체
 */
@Service
@RequiredArgsConstructor
public class ScheduleServieImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AuthorRepository authorRepository;

    /**
     * 일정을 생성합니다.
     *
     * @param requestDto 일정 등록 요청 DTO
     * @return 일정 응답 DTO
     */
    @Override
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Author author = authorRepository.findByIdOrElseThrow(requestDto.getAuthorId());

        Schedule schedule = new Schedule(
                requestDto.getScheduleDate(),
                requestDto.getTaskContent(),
                requestDto.getFocusTag(),
                requestDto.getPassword(),
                author
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(
                savedSchedule.getId(),
                savedSchedule.getScheduleDate(),
                savedSchedule.getTaskContent(),
                savedSchedule.getFocusTag(),
                new ScheduleResponseDto.AuthorInfo(
                        author.getId(),
                        author.getName(),
                        author.getEmail()
                ),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }
}
