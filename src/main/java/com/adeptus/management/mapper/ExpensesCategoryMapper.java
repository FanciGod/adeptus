package com.adeptus.management.mapper;

import com.adeptus.management.dto.ExpensesCategoryDTO;
import com.adeptus.management.entity.expenses.ExpensesCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpensesCategoryMapper {
    ExpensesCategoryDTO toDTO(ExpensesCategory entity);
    ExpensesCategory toEntity(ExpensesCategoryDTO dto);
}