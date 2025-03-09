package com.adeptus.management.service;

import com.adeptus.management.dto.ExpensesCategoryDTO;

import java.util.List;

public interface IExpensesCategoryService {
    ExpensesCategoryDTO create(ExpensesCategoryDTO dto);
    ExpensesCategoryDTO getById(Long id);
    List<ExpensesCategoryDTO> getAll();
    ExpensesCategoryDTO update(Long id, ExpensesCategoryDTO dto);
    void delete(Long id);
}