package com.adeptus.management.repository;

import com.adeptus.management.entity.expenses.ExpensesCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesCategoryRepository extends JpaRepository<ExpensesCategory, Long> {
    boolean existsByName(String name);
}
