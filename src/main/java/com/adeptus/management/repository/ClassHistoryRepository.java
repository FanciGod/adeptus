package com.adeptus.management.repository;

import com.adeptus.management.entity.classes.ClassHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassHistoryRepository extends JpaRepository<ClassHistory,Long> {
}
