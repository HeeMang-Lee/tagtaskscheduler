package com.example.tagtaskschedule.schedule.controller;

import com.example.tagtaskschedule.schedule.dto.ScheduleRequestDto;
import com.example.tagtaskschedule.schedule.dto.ScheduleResponseDto;
import com.example.tagtaskschedule.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 일정 등록
     *
     * @param requestDto 일정 등록 요청 DTO
     * @return 일정 등록 응답 DTO
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody @Valid ScheduleRequestDto requestDto) {
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * 일정 단일 조회
     *
     * @param id 일정 ID
     * @return 일정 응답 DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {
        ScheduleResponseDto responseDto = scheduleService.getSchedule(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 일정 전체 조회 (선택 필터: authorId, focusTag)
     *
     * @param authorId
     * @param focusTag
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedule(
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) String focusTag
    ) {
        List<ScheduleResponseDto> responseDtos = scheduleService.getSchedules(authorId, focusTag);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    /**
     * 일정 수정
     *
     * @param id 일정 ID
     * @param requestDto 일정 수정 요청 DTO
     * @return 일정 응답 DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody @Valid ScheduleRequestDto requestDto
    ) {
        ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
