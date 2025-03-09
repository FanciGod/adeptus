package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.ExpensesCategoryDTO;
import com.adeptus.management.entity.expenses.ExpensesCategory;
import com.adeptus.management.exception.EntityDuplicateException;
import com.adeptus.management.exception.EntityNotFoundException;
import com.adeptus.management.mapper.ExpensesCategoryMapper;
import com.adeptus.management.repository.ExpensesCategoryRepository;
import com.adeptus.management.service.IExpensesCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpensesCategoryServiceImpl implements IExpensesCategoryService {

    private final ExpensesCategoryRepository repository;
    private final ExpensesCategoryMapper mapper;

    @Override
    @Transactional
    public ExpensesCategoryDTO create(ExpensesCategoryDTO dto) {
        // Kiểm tra xem tên danh mục đã tồn tại chưa
        if (repository.existsByName(dto.getName())) {
            throw new EntityDuplicateException(dto.getName());
        }

        ExpensesCategory entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public ExpensesCategoryDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("ExpensesCategory", id));
    }

    @Override
    public List<ExpensesCategoryDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ExpensesCategoryDTO update(Long id, ExpensesCategoryDTO dto) {
        ExpensesCategory entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ExpensesCategory", id));

        // Kiểm tra xem tên danh mục đã tồn tại chưa, nhưng bỏ qua chính nó
        if (!entity.getName().equals(dto.getName()) && repository.existsByName(dto.getName())) {
            throw new EntityDuplicateException(dto.getName());
        }

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
