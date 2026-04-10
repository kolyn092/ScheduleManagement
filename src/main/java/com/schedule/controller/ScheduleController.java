package com.schedule.controller;

import com.schedule.dto.request.CreateScheduleRequest;
import com.schedule.dto.request.DeleteScheduleRequest;
import com.schedule.dto.request.UpdateScheduleRequest;
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

    @GetMapping("/api/schedules/{id}")
    public ResponseEntity<ScheduleResponse> get(@PathVariable Long id) {
        var res = scheduleService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PatchMapping("/api/schedules/{id}")
    public ResponseEntity<ScheduleResponse> update(@PathVariable Long id, @RequestBody UpdateScheduleRequest req) {
        ScheduleResponse res;
        try {
            res = scheduleService.update(id, req);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/api/schedules/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody DeleteScheduleRequest req) {
        try {
            scheduleService.delete(id, req);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
