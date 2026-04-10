package com.schedule.service;

import com.schedule.dto.request.CreateScheduleRequest;
import com.schedule.dto.request.DeleteScheduleRequest;
import com.schedule.dto.request.UpdateScheduleRequest;
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

    @Transactional(readOnly = true)
    public ScheduleResponse get(Long id) {
        var schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return ScheduleResponse.from(schedule);
    }

    @Transactional
    public ScheduleResponse update(Long id, UpdateScheduleRequest req) {
        var schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        if (schedule.getPassword().equals(req.getPassword())) {
            schedule.update(req);
            scheduleRepository.saveAndFlush(schedule);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return ScheduleResponse.from(schedule);
    }

    @Transactional
    public void delete(Long id, DeleteScheduleRequest req) {
        var schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        if (!schedule.getPassword().equals(req.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.deleteById(id);
    }
}
