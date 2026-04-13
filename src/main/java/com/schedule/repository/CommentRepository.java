package com.schedule.repository;

import com.schedule.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByScheduleId(Long id);
    long countByScheduleId(Long id);
}
