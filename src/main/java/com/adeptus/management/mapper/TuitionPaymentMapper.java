package com.adeptus.management.mapper;

import com.adeptus.management.dto.request.student.TuitionPaymentRequest;
import com.adeptus.management.dto.response.TuitionPaymentResponse;
import com.adeptus.management.entity.student.Student;
import com.adeptus.management.entity.student.TuitionPayment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TuitionPaymentMapper {

    @Mapping(source = "student.id", target = "studentId")
    TuitionPaymentResponse toResponse(TuitionPayment tuitionPayment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "total", expression = "java(request.getAmount() * (100 - request.getDiscount()) / 100)")
    TuitionPayment toEntity(TuitionPaymentRequest request, Student student);
}