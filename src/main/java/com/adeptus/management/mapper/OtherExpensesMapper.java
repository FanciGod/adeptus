package com.adeptus.management.mapper;

import com.adeptus.management.dto.OtherExpensesResponseDTO;
import com.adeptus.management.entity.expenses.OtherExpenses;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ExpensesCategoryMapper.class)
public interface OtherExpensesMapper {
    // Map Entity -> ResponseDTO
    @Mapping(target = "category", source = "expensesCategory") // Trả về full category
    OtherExpensesResponseDTO toResponseDTO(OtherExpenses entity);
}
