package com.schedule.dto.response;

import com.schedule.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CommentResponse {
    private final Long id;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentResponse(Long id, String content, String author,
                           LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getAuthor(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

    public static List<CommentResponse> from(List<Comment> comments) {
        List<CommentResponse> dtos = new ArrayList<>();
        for (var comment : comments) {
            dtos.add(from(comment));
        }
        return dtos;
    }
}