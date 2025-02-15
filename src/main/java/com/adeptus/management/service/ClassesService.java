package com.adeptus.management.service;

import com.adeptus.management.dto.request.classes.CreateClassRequest;
import com.adeptus.management.dto.request.classes.UpdateClassRequest;
import com.adeptus.management.dto.response.ClassDetailResponse;

import java.util.List;

public interface ClassesService {
    List<ClassDetailResponse> getAllClasses();
    ClassDetailResponse getClassById(Long id);
    ClassDetailResponse createClass(CreateClassRequest request);
    ClassDetailResponse updateClass(Long id, UpdateClassRequest request);
    void deleteClass(Long id);
}
