package com.example.tagtaskschedule.service;

import com.example.tagtaskschedule.dto.ScheduleRequestDto;
import com.example.tagtaskschedule.dto.ScheduleResponseDto;
import com.example.tagtaskschedule.entity.Author;
import com.example.tagtaskschedule.entity.Schedule;
import com.example.tagtaskschedule.repository.AuthorRepository;
import com.example.tagtaskschedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
                author
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return toDto(savedSchedule);
    }

    /**
     * 일정 단일 조회
     *
     * @param id 일정 ID
     * @return 일정 응답 DTO
     */
    @Override
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        return toDto(schedule);
    }

    /**
     * 일정 전체 조회 (필터 조건: 작성자 ID, 집중 테마)
     *
     * @param authorId 일정 ID (nallable)
     * @param focusTag 집중 태그(nullable)
     * @return 일정 목록 응답 DTO
     */
    @Override
    public List<ScheduleResponseDto> getSchedules(Long authorId, String focusTag) {
        return scheduleRepository.findAll().stream()
                .filter(schdule -> authorId == null || schdule.getAuthor().getId().equals(authorId))
                .filter(schedule -> focusTag == null || schedule.getFocusTag().name().equalsIgnoreCase(focusTag))
                .map(this::toDto)
                .toList();
    }

    /**
     * 일정 수정
     *
     * @param id 일정 ID
     * @param requestDto 일정 수정 요청 DTO
     * @return 수정된 일정 응답 DTO
     */
    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        Author author = authorRepository.findByIdOrElseThrow(requestDto.getAuthorId());

        schedule.update(
                requestDto.getScheduleDate(),
                requestDto.getTaskContent(),
                requestDto.getFocusTag(),
                author
        );

        return toDto(schedule);
    }

    /**
     * 일정 삭제
     *
     * @param id
     */
    @Override
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(schedule);
    }

    /**
     * Schedule -> DTO 변환
     *
     * @param schedule 일정 엔티티
     * @return 응답 DTO
     */
    private ScheduleResponseDto toDto(Schedule schedule) {
        Author author = schedule.getAuthor();
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getScheduleDate(),
                schedule.getTaskContent(),
                schedule.getFocusTag(),
                new ScheduleResponseDto.AuthorInfo(author.getId(), author.getName(), author.getEmail() ),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
