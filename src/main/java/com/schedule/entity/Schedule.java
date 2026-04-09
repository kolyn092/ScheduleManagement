package com.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Entity
@Table(name = "Schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

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

    private Date createAt;
    private Date modifiedAt;
}
