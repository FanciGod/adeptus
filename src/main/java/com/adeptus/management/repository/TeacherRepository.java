package com.adeptus.management.repository;

import com.adeptus.management.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("select a from Teacher a where a.isActive = true and a.id = ?1")
    Optional<Teacher> findByIdAndIsActiveTrue(Long id);

    List<Teacher> findByIsActiveTrue();

    boolean existsByPhoneAndIsActiveTrue(String phone);
}
