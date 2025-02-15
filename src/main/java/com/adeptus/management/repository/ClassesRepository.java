package com.adeptus.management.repository;

import com.adeptus.management.entity.classes.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
    @Query("SELECT c FROM Classes c WHERE c.isActive = true")
    List<Classes> findAllActiveClasses();
    Optional<Classes> findByClassName(String className);
}
