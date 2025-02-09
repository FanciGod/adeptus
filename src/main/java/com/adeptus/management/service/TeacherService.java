package com.adeptus.management.service;

import com.adeptus.management.dto.request.teacher.CreateTeacherRequest;
import com.adeptus.management.dto.request.teacher.UpdateTeacherRequest;
import com.adeptus.management.dto.response.TeacherResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {
    List<TeacherResponse> getAllTeachers();

    Page<TeacherResponse> getAllTeachersWithPagination(int page, int size);

    TeacherResponse getTeacherById(Long id);

    TeacherResponse createTeacher(CreateTeacherRequest request);

    TeacherResponse updateTeacher(Long id, UpdateTeacherRequest request);

    void deleteTeacher(Long id);
}
