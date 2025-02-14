package com.adeptus.management.repository;

import com.adeptus.management.entity.teacher.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM Teacher t LEFT JOIN FETCH t.salaries LEFT JOIN FETCH t.classes WHERE t.isActive = true")
    List<Teacher> getAllActiveTeachers();
//    @Query("SELECT t FROM Teacher t LEFT JOIN FETCH t.classes c WHERE t.isActive = true")
//    List<Teacher> getAllActiveTeachers();

    @Query("SELECT t FROM Teacher t LEFT JOIN FETCH t.salaries LEFT JOIN FETCH t.classes WHERE t.isActive = true")
    Page<Teacher> getAllActiveTeachers(Pageable pageable);

    @Query("SELECT t FROM Teacher t LEFT JOIN FETCH t.salaries LEFT JOIN FETCH t.classes WHERE t.isActive = true AND t.id = ?1")
    Optional<Teacher> findActiveTeacherById(Long id);
//    @Query("SELECT t FROM Teacher t LEFT JOIN FETCH t.classes c WHERE t.isActive = true AND t.id = ?1")
//    Optional<Teacher> findActiveTeacherById(Long id);
}
