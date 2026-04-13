package com.schedule.dto.response;

import com.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleDetailResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final List<CommentResponse> comments;

    public ScheduleDetailResponse(Long id, String title, String content, String author,
                            LocalDateTime createdAt, LocalDateTime modifiedAt, List<CommentResponse> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
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
