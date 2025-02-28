package com.adeptus.management.repository;

import com.adeptus.management.entity.student.TuitionPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TuitionPaymentRepository extends JpaRepository<TuitionPayment, Long> {
    List<TuitionPayment> findByStudentId(Long studentId);
}
