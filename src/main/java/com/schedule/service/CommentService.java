package com.schedule.service;

import com.schedule.dto.request.CreateCommentRequest;
import com.schedule.dto.response.CommentResponse;
import com.schedule.entity.Comment;
import com.schedule.repository.CommentRepository;
import com.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public CommentResponse create(Long scheduleId, CreateCommentRequest req) {
        req.validation();   // 입력 값 검증
        var schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        if (schedule.getCommentCount() >= 10) {
            throw new IllegalArgumentException("하나의 일정에는 댓글은 10개까지만 작성할 수 있습니다.");
        }
        var comment = Comment.to(req, schedule);
        var res = commentRepository.save(comment);
        return CommentResponse.from(res);
    }
}
