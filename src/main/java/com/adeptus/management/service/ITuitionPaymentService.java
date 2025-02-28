package com.adeptus.management.service;

import com.adeptus.management.dto.request.student.TuitionPaymentRequest;
import com.adeptus.management.dto.response.TuitionPaymentResponse;

import java.util.List;

public interface ITuitionPaymentService {
    TuitionPaymentResponse createTuitionPayment(TuitionPaymentRequest request);
    List<TuitionPaymentResponse> getTuitionPaymentsByStudent(Long studentId);
    List<TuitionPaymentResponse> getAllTuitionPayments();
}
