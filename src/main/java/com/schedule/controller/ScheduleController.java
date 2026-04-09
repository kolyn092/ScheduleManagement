package com.schedule.controller;

import com.schedule.dto.request.CreateScheduleRequest;
import com.schedule.dto.response.ScheduleResponse;
import com.schedule.entity.Schedule;
import com.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/api/schedules")
    public ResponseEntity<ScheduleResponse> create(@RequestBody CreateScheduleRequest req) {
        var res = scheduleService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/api/schedules")
    public ResponseEntity<List<ScheduleResponse>> getAll(@RequestParam(required = false) String author) {
        var res = scheduleService.getAll(author);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
