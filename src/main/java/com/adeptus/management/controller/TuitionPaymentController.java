package com.adeptus.management.controller;

import com.adeptus.management.dto.request.student.TuitionPaymentRequest;
import com.adeptus.management.dto.response.TuitionPaymentResponse;
import com.adeptus.management.entity.student.TuitionPayment;
import com.adeptus.management.service.ITuitionPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tuition-payment")
@RequiredArgsConstructor
public class TuitionPaymentController {

    private final ITuitionPaymentService tuitionPaymentService;
    @GetMapping
    public ResponseEntity<List<TuitionPaymentResponse>> getAllTuitionPayments() {
        return ResponseEntity.ok(tuitionPaymentService.getAllTuitionPayments());
    }
    @PostMapping
    public ResponseEntity<TuitionPaymentResponse> createTuitionPayment(
            @Valid @RequestBody TuitionPaymentRequest request) {
        return ResponseEntity.ok(tuitionPaymentService.createTuitionPayment(request));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<TuitionPaymentResponse>> getPaymentsByStudent(@PathVariable Long id) {
        return ResponseEntity.ok(tuitionPaymentService.getTuitionPaymentsByStudent(id));
    }
}
