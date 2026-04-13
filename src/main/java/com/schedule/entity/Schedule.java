package com.schedule.entity;

import com.schedule.dto.request.CreateScheduleRequest;
import com.schedule.dto.request.UpdateScheduleRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "Schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임
    private Long id;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String author;

    @Column(length = 128, nullable = false)
    private String password;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Schedule(String title, String content, String author, String password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }

    public static Schedule to(CreateScheduleRequest req) {
        return new Schedule(
                req.getTitle(),
                req.getContent(),
                req.getAuthor(),
                req.getPassword()
        );
    }

    public void update(UpdateScheduleRequest req) {
        this.title = req.getTitle();
        this.author = req.getAuthor();
    }

    public int getCommentCount() {
        return this.comments.size();
    }
}
