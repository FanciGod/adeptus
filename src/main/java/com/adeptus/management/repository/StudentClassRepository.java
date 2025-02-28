package com.adeptus.management.repository;

import com.adeptus.management.entity.classes.Classes;
import com.adeptus.management.entity.student.Student;
import com.adeptus.management.entity.student.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {
    boolean existsByStudentAndClasses(Student student, Classes classes);
    void deleteByStudentIdAndClassesId(Long studentId, Long classId);

    List<StudentClass> findByStudentId(Long id);

    Optional<StudentClass> findByStudentAndClasses(Student student, Classes classes);

}
