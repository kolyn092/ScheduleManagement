package com.schedule.dto.request;

import lombok.Getter;

@Getter
public class UpdateScheduleRequest {
    private String title;
    private String author;
    private String password;

    public void validation() {
        if (title == null || title.isEmpty() || title.isBlank()) {
            throw new IllegalArgumentException("필수 입력 값이 입력되지 않았습니다.");
        }

        if (author == null || author.isEmpty() || author.isBlank()) {
            throw new IllegalArgumentException("필수 입력 값이 입력되지 않았습니다.");
        }

        if (password == null || password.isEmpty() || password.isBlank()) {
            throw new IllegalArgumentException("필수 입력 값이 입력되지 않았습니다.");
        }

        if (title.length() >= 30) {
            throw new IllegalArgumentException("30자 이내로 작성해주세요.");
        }
    }
}
