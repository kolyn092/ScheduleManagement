package com.schedule.dto.request;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String content;
    private String author;
    private String password;
}
