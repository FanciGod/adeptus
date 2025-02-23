package com.adeptus.management.service;

import com.adeptus.management.dto.ApiResponse;
import com.adeptus.management.dto.request.student.AddStudentToClassRequest;
import com.adeptus.management.dto.request.student.CreateMarkRequest;
import com.adeptus.management.dto.request.student.CreateStudentRequest;
import com.adeptus.management.dto.request.student.UpdateStudentRequest;
import com.adeptus.management.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(Long id);
    StudentResponse createStudent(CreateStudentRequest request);
    StudentResponse updateStudent(Long id, UpdateStudentRequest request);
    ApiResponse<String> deleteStudent(Long id);
    ApiResponse<String> addStudentToClass(AddStudentToClassRequest request);
    ApiResponse<String> removeStudentFromClass(Long studentId, Long classId);
    ApiResponse<String> addMarkForStudent(CreateMarkRequest request);
}
