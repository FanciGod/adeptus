package com.adeptus.management.controller;

import com.adeptus.management.dto.OtherExpensesRequestDTO;
import com.adeptus.management.dto.OtherExpensesResponseDTO;
import com.adeptus.management.service.IOtherExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/other-expenses")
@RequiredArgsConstructor
public class OtherExpensesController {

    private final IOtherExpensesService service;

    @PostMapping
    public ResponseEntity<OtherExpensesResponseDTO> create(@RequestBody OtherExpensesRequestDTO request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OtherExpensesResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<OtherExpensesResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<OtherExpensesResponseDTO>> getByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(service.getByCategoryId(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OtherExpensesResponseDTO> update(@PathVariable Long id, @RequestBody OtherExpensesRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Expense deleted successfully.");
    }
}
