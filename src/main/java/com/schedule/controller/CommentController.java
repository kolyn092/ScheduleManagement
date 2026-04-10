package com.schedule.controller;

import com.schedule.dto.request.CreateCommentRequest;
import com.schedule.dto.response.CommentResponse;
import com.schedule.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/api/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponse> create(@PathVariable Long scheduleId, @RequestBody CreateCommentRequest req) {
        CommentResponse res;
        try {
            res = commentService.create(scheduleId, req);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
}