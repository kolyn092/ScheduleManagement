package com.schedule.dto.response;

import com.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleDetailResponse extends ScheduleResponse {
    private final List<CommentResponse> comments;

    public ScheduleDetailResponse(Long id, String title, String content, String author,
                            LocalDateTime createdAt, LocalDateTime modifiedAt, List<CommentResponse> comments) {
        super(id, title, content,author, createdAt, modifiedAt);
        this.comments = comments;
    }

    public static ScheduleDetailResponse from(Schedule schedule, List<CommentResponse> comments) {
        return new ScheduleDetailResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                comments
        );
    }
}
