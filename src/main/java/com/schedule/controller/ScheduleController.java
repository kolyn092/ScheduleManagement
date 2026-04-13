package com.schedule.controller;

import com.schedule.dto.request.CreateScheduleRequest;
import com.schedule.dto.request.DeleteScheduleRequest;
import com.schedule.dto.request.UpdateScheduleRequest;
import com.schedule.dto.response.ApiResponse;
import com.schedule.dto.response.ScheduleDetailResponse;
import com.schedule.dto.response.ScheduleResponse;
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
    public ResponseEntity<ApiResponse<ScheduleResponse>> create(@RequestBody CreateScheduleRequest req) {
        ScheduleResponse res;
        try {
            res = scheduleService.create(req);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("생성 완료", res));
    }

    @GetMapping("/api/schedules")
    public ResponseEntity<ApiResponse<List<ScheduleResponse>>> getAll(@RequestParam(required = false) String author) {
        var res = scheduleService.getAll(author);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("조회 완료", res));
    }

    @GetMapping("/api/schedules/{id}")
    public ResponseEntity<ApiResponse<ScheduleDetailResponse>> get(@PathVariable Long id) {
        ScheduleDetailResponse res;
        try {
            res = scheduleService.get(id);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.fail(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("조회 완료", res));
    }

    @PatchMapping("/api/schedules/{id}")
    public ResponseEntity<ApiResponse<ScheduleResponse>> update(@PathVariable Long id, @RequestBody UpdateScheduleRequest req) {
        ScheduleResponse res;
        try {
            res = scheduleService.update(id, req);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.fail(e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("수정 완료", res));
    }

    @DeleteMapping("/api/schedules/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id, @RequestBody DeleteScheduleRequest req) {
        try {
            scheduleService.delete(id, req);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.fail(e.getMessage()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.fail(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("삭제 완료", null));
    }
}
