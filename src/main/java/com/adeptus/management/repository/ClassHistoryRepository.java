package com.adeptus.management.repository;

import com.adeptus.management.entity.attendance.ClassHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassHistoryRepository extends JpaRepository<ClassHistory, Long> {
    List<ClassHistory> findByClassesId(Long classId);
}
