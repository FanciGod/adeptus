package com.adeptus.management.repository;

import com.adeptus.management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findByIsActiveTrue();
    Optional<Course> findByIdAndIsActiveTrue(Long id);
}
