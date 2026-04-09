package com.schedule.dto.request;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String title;
    private String author;
    private String password;
}
