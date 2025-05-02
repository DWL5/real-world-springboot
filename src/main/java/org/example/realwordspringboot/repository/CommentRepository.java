package org.example.realwordspringboot.repository;

import org.example.realwordspringboot.repository.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
