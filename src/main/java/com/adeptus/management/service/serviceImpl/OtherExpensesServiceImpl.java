package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.OtherExpensesRequestDTO;
import com.adeptus.management.dto.OtherExpensesResponseDTO;
import com.adeptus.management.entity.expenses.ExpensesCategory;
import com.adeptus.management.entity.expenses.OtherExpenses;
import com.adeptus.management.exception.EntityNotFoundException;
import com.adeptus.management.mapper.OtherExpensesMapper;
import com.adeptus.management.repository.ExpensesCategoryRepository;
import com.adeptus.management.repository.OtherExpensesRepository;
import com.adeptus.management.service.IOtherExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OtherExpensesServiceImpl implements IOtherExpensesService {

    private final OtherExpensesRepository repository;
    private final ExpensesCategoryRepository categoryRepository;
    private final OtherExpensesMapper mapper;

    @Override
    @Transactional
    public OtherExpensesResponseDTO create(OtherExpensesRequestDTO dto) {
        ExpensesCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("ExpensesCategory", dto.getCategoryId()));

        OtherExpenses entity = OtherExpenses.builder()
                .expensesCategory(category)
                .total(dto.getTotal())
                .date(dto.getDate())
                .description(dto.getDescription())
                .build();

        return mapper.toResponseDTO(repository.save(entity));
    }

    @Override
    public OtherExpensesResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("OtherExpenses", id));
    }

    @Override
    public List<OtherExpensesResponseDTO> getAll() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<OtherExpensesResponseDTO> getByCategoryId(Long categoryId) {
        return repository.findByExpensesCategory_Id(categoryId).stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OtherExpensesResponseDTO update(Long id, OtherExpensesRequestDTO dto) {
        OtherExpenses entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OtherExpenses", id));

        ExpensesCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("ExpensesCategory", dto.getCategoryId()));

        entity.setTotal(dto.getTotal());
        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());
        entity.setExpensesCategory(category);

        return mapper.toResponseDTO(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("OtherExpenses", id);
        }
        repository.deleteById(id);
    }
}
