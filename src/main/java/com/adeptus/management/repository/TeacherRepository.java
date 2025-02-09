package com.adeptus.management.repository;

import com.adeptus.management.entity.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select t from Teacher t where t.isActive = true")
    List<Teacher> getAllActiveTeachers();

    @Query("select t from Teacher t where t.isActive = true")
    Page<Teacher> getAllActiveTeachers(Pageable pageable);

    @Query("select t from Teacher t where t.isActive = true and t.id = ?1")
    Optional<Teacher> findActiveTeacherById(Long id);
}
