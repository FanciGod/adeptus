package com.adeptus.management.repository;

import com.adeptus.management.entity.student.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark,Long> {
    List<Mark> findByStudentId(Long studentId);
}
