package com.adeptus.management.service;

import com.adeptus.management.dto.request.CreateNewClassRequest;
import com.adeptus.management.dto.request.UpdateClassRequest;
import com.adeptus.management.dto.response.ClassesResponse;
import org.springframework.data.domain.Page;

public interface ClassesService {
    Page<ClassesResponse> findAllClassesWithPagination(int page, int size);

    ClassesResponse findClassById(Long id);

    ClassesResponse createNewClass(CreateNewClassRequest request);

    ClassesResponse updateClassById(Long id, UpdateClassRequest request);

    void deleteClassById(Long id);
}
