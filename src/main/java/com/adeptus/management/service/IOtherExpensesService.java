package com.adeptus.management.service;

import com.adeptus.management.dto.OtherExpensesRequestDTO;
import com.adeptus.management.dto.OtherExpensesResponseDTO;

import java.util.List;

public interface IOtherExpensesService {
    OtherExpensesResponseDTO create(OtherExpensesRequestDTO dto);
    OtherExpensesResponseDTO getById(Long id);
    List<OtherExpensesResponseDTO> getAll();
    List<OtherExpensesResponseDTO> getByCategoryId(Long categoryId);
    OtherExpensesResponseDTO update(Long id, OtherExpensesRequestDTO dto);
    void delete(Long id);
}
