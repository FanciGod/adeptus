package com.adeptus.management.repository;

import com.adeptus.management.entity.Classes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
    @Query("select a from Classes a where a.isActive = true")
    Page<Classes> findAllActiveClassesWithPagination(Pageable pageable);

    @Query("select a from Classes a where a.isActive = true and a.id = ?1")
    Optional<Classes> findAllActiveClassById(Long id);

     boolean existsByClassName(String className);
}
