package com.adeptus.management.repository;

import com.adeptus.management.entity.teacher.TeacherSalaryHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherSalaryHistoryRepository extends JpaRepository<TeacherSalaryHistory, Long> {
    List<TeacherSalaryHistory> findByTeacherId(Long teacherId);
}
