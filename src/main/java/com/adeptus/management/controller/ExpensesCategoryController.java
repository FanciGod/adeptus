package com.adeptus.management.controller;
import com.adeptus.management.dto.ExpensesCategoryDTO;
import com.adeptus.management.service.IExpensesCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses-category")
@RequiredArgsConstructor
public class ExpensesCategoryController {

    private final IExpensesCategoryService service;

    @PostMapping
    public ResponseEntity<ExpensesCategoryDTO> create(@RequestBody ExpensesCategoryDTO request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpensesCategoryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ExpensesCategoryDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpensesCategoryDTO> update(@PathVariable Long id, @RequestBody ExpensesCategoryDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Category deleted successfully.");
    }
}