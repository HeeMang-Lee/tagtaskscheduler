package com.example.tagtaskschedule.schedule.repository;

import com.example.tagtaskschedule.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * 일정(Schedule) 엔티티에 대한 데이터 접근 레포지토리
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    /**
     * ID로 일정을 조회하거나 없을 경우 예외를 발생시킵니다.
     *
     * @param id
     * @return
     */
    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "일정 ID가 존재하지 않습니다. id = " + id) );
    }
}
