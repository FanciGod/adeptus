package com.adeptus.management.repository;

import com.adeptus.management.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
    @Query("select a from Staff a where a.isActive = true")
    List<Staff> getAllStaffs();

    @Query("select a from Staff a where a.isActive = true")
    Page<Staff> getAllStaffsWithPagination(Pageable pageable);

    @Query("select a from Staff a where a.isActive = true and a.username = ?1")
    Optional<Staff> findActiveStaffByUsername(String username);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);
}
