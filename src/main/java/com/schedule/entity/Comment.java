package com.schedule.entity;

import com.schedule.dto.request.CreateCommentRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "Comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String author;

    @Column(length = 128, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Comment(String content, String author, String password, Schedule schedule) {
        this.content = content;
        this.author = author;
        this.password = password;
        this.schedule = schedule;
    }

    public static Comment to(CreateCommentRequest req, Schedule schedule) {
        return new Comment(
                req.getContent(),
                req.getAuthor(),
                req.getPassword(),
                schedule
        );
    }
}
