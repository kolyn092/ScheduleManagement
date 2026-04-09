package com.schedule.dto.response;

import com.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ScheduleResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleResponse(Long id, String title, String content, String author,
                                  LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = createdAt;
    }

    public static ScheduleResponse from(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt()
        );
    }

    public static List<ScheduleResponse> from(List<Schedule> schedules) {
        List<ScheduleResponse> dtos = new ArrayList<>();
        for (var schedule : schedules) {
            dtos.add(from(schedule));
        }
        return dtos;
    }
}
