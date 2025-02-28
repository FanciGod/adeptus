package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.request.student.TuitionPaymentRequest;
import com.adeptus.management.dto.response.TuitionPaymentResponse;
import com.adeptus.management.entity.student.Student;
import com.adeptus.management.entity.student.StudentClass;
import com.adeptus.management.entity.student.TuitionPayment;
import com.adeptus.management.exception.EntityNotFoundException;
import com.adeptus.management.mapper.TuitionPaymentMapper;
import com.adeptus.management.repository.ClassesRepository;
import com.adeptus.management.repository.StudentClassRepository;
import com.adeptus.management.repository.StudentRepository;
import com.adeptus.management.repository.TuitionPaymentRepository;
import com.adeptus.management.service.ITuitionPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TuitionPaymentServiceImpl implements ITuitionPaymentService {

    private final TuitionPaymentRepository tuitionPaymentRepository;
    private final StudentRepository studentRepository;
    private final StudentClassRepository studentClassRepository;
    private final ClassesRepository classesRepository;
    private final TuitionPaymentMapper tuitionPaymentMapper;

    @Override
    @Transactional
    public TuitionPaymentResponse createTuitionPayment(@Valid TuitionPaymentRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student", request.getStudentId()));

        Long totalAmount = request.getAmount() * (100 - request.getDiscount()) / 100; // Áp dụng giảm giá

        TuitionPayment tuitionPayment = tuitionPaymentMapper.toEntity(request, student);
        tuitionPayment.setTotal(totalAmount); // Đảm bảo tính toán chính xác
        tuitionPaymentRepository.save(tuitionPayment);

        // Cập nhật số buổi học còn lại của học viên trong tất cả các lớp
        List<StudentClass> studentClasses = studentClassRepository.findByStudentId(student.getId());
        for (StudentClass studentClass : studentClasses) {
            studentClass.setLessonRemain(studentClass.getLessonRemain() + request.getLessonPurchased());
            studentClassRepository.save(studentClass);
        }

        return tuitionPaymentMapper.toResponse(tuitionPayment);
    }

    @Override
    public List<TuitionPaymentResponse> getTuitionPaymentsByStudent(Long studentId) {
        List<TuitionPayment> payments = tuitionPaymentRepository.findByStudentId(studentId);
        if (payments.isEmpty()) {
            throw new EntityNotFoundException("Không tìm thấy lịch sử thanh toán của học viên ID " + studentId);
        }
        return payments.stream().map(tuitionPaymentMapper::toResponse).toList();
    }

    @Override
    public List<TuitionPaymentResponse> getAllTuitionPayments() {
        List<TuitionPayment> payments = tuitionPaymentRepository.findAll();
        if (payments.isEmpty()) {
            throw new EntityNotFoundException("Không có thanh toán học phí nào được ghi nhận");
        }
        return payments.stream().map(tuitionPaymentMapper::toResponse).toList();
    }
}