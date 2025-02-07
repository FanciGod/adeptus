package com.adeptus.management.repository;

import com.adeptus.management.entity.staff.StaffSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffSalaryRepository extends JpaRepository<StaffSalary, Long> {
}
