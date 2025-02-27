package com.adeptus.management.service;

import com.adeptus.management.dto.request.CreateNewTeacherRequest;
import com.adeptus.management.dto.request.UpdateTeacherRequest;
import com.adeptus.management.dto.response.TeacherResponse;

import java.util.List;

public interface TeacherService {
    List<TeacherResponse> getAllActiveTeacher();

    TeacherResponse getTeacherById(Long id);

    TeacherResponse CreateNewTeacher(CreateNewTeacherRequest request);

    TeacherResponse updateTeacherById(UpdateTeacherRequest request, Long id);

    void deleteTeacherById(Long id);
}
