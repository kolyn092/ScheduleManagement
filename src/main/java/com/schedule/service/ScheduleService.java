package com.schedule.service;

import com.schedule.dto.request.CreateScheduleRequest;
import com.schedule.dto.response.ScheduleResponse;
import com.schedule.entity.Schedule;
import com.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 일정 생성
    @Transactional
    public ScheduleResponse create(CreateScheduleRequest req) {
        var schedule = Schedule.to(req);
        var res = scheduleRepository.save(schedule);
        return ScheduleResponse.from(res);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponse> getAll(String author) {
        List<Schedule> schedules;
        if (author == null || author.isBlank()) {
            schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
        } else {
            schedules = scheduleRepository.findByAuthorOrderByModifiedAtDesc(author);
        }
        return ScheduleResponse.from(schedules);
    }
}
