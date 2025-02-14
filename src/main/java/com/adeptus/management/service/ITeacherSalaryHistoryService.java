package com.adeptus.management.service;

import com.adeptus.management.dto.request.teacher.TeacherSalaryHistoryCreateRequest;
import com.adeptus.management.dto.request.teacher.TeacherSalaryHistoryUpdateRequest;
import com.adeptus.management.dto.response.TeacherSalaryHistoryResponse;

import java.util.List;

public interface ITeacherSalaryHistoryService {
    TeacherSalaryHistoryResponse getById(Long id);
    List<TeacherSalaryHistoryResponse> getByTeacherId(Long teacherId);
    List<TeacherSalaryHistoryResponse> getAll();  // New method for getting all salary histories
    TeacherSalaryHistoryResponse create(TeacherSalaryHistoryCreateRequest request);
    TeacherSalaryHistoryResponse update(Long id, TeacherSalaryHistoryUpdateRequest request);
    void delete(Long id);
}
