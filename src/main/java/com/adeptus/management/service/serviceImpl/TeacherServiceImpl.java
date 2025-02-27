package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.request.CreateNewTeacherRequest;
import com.adeptus.management.dto.request.UpdateTeacherRequest;
import com.adeptus.management.dto.response.TeacherResponse;
import com.adeptus.management.entity.Teacher;
import com.adeptus.management.entity.TeacherSalary;
import com.adeptus.management.exception.AppException;
import com.adeptus.management.exception.ErrorCode;
import com.adeptus.management.mapper.ClassesMapper;
import com.adeptus.management.mapper.TeacherMapper;
import com.adeptus.management.mapper.TeacherSalaryMapper;
import com.adeptus.management.repository.TeacherRepository;
import com.adeptus.management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;
    private final TeacherSalaryMapper teacherSalaryMapper;
    private final ClassesMapper classesMapper;

    @Override
    public List<TeacherResponse> getAllActiveTeacher() {
        return teacherRepository.findByIsActiveTrue().stream().map(this::toTeacherResponse).toList();
    }

    @Override
    public TeacherResponse getTeacherById(Long id) {
        var teacher = teacherRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new AppException(ErrorCode.TEACHER_ID_NOT_FOUND));
        return toTeacherResponse(teacher);
    }

    @Override
    public TeacherResponse CreateNewTeacher(CreateNewTeacherRequest request) {
        if (teacherRepository.existsByPhoneAndIsActiveTrue(request.getPhone())) {
            throw new AppException(ErrorCode.PHONE_DUPLICATED);
        }
        var teacher = teacherMapper.toTeacher(request);

        TeacherSalary teacherSalary = TeacherSalary.builder()
                .teacher(teacher)
                .salaryPerSession(request.getSalaryPerSession())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(30))
                .build();
        teacher.setSalaries(new ArrayList<>());
        teacher.getSalaries().add(teacherSalary);

        var newTeacher = teacherRepository.save(teacher);
        return toTeacherResponse(newTeacher);
    }

    @Override
    public TeacherResponse updateTeacherById(UpdateTeacherRequest request, Long id) {
        var teacher = teacherRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new AppException(ErrorCode.TEACHER_ID_NOT_FOUND));
        teacherMapper.updateTeacher(request, teacher);
        return toTeacherResponse(teacherRepository.save(teacher));

    }

    @Override
    public void deleteTeacherById(Long id) {
        var teacher = teacherRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new AppException(ErrorCode.TEACHER_ID_NOT_FOUND));
        teacher.setIsActive(false);
        teacherRepository.save(teacher);
    }

    private TeacherResponse toTeacherResponse(Teacher teacher) {
        TeacherResponse teacherResponse = teacherMapper.toTeacherResponse(teacher);
        if (teacher.getSalaries() != null) {
            teacherResponse.setTeacherSalaryDtoList(teacher.getSalaries().stream().map(teacherSalaryMapper::toTeacherSalaryDto).toList());
        }
        if (teacher.getAClasses() != null) {
            teacherResponse.setClassesDtoList(teacher.getAClasses().stream().map(classesMapper::toClassesDto).toList());
        }

        return teacherResponse;
    }
}
