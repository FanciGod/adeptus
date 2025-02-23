package com.adeptus.management.repository;

import com.adeptus.management.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByIsActiveTrue();

    Optional<Student> findByEmailAndIsActiveTrue(String email);

    Optional<Student> findByPhoneAndIsActiveTrue(String phone);
    Optional<Student> findByEmail(String email);
    Optional<Student> findByPhone(String phone);
    boolean existsByEmailAndIsActiveTrue(String email);

    boolean existsByPhoneAndIsActiveTrue(String phone);

}

