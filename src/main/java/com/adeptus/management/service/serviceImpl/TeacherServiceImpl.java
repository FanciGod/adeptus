package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.request.teacher.CreateTeacherRequest;
import com.adeptus.management.dto.request.teacher.UpdateTeacherRequest;
import com.adeptus.management.dto.response.TeacherResponse;
import com.adeptus.management.entity.teacher.Teacher;
import com.adeptus.management.exception.EntityNotFoundException;
import com.adeptus.management.mapper.TeacherMapper;
import com.adeptus.management.repository.TeacherRepository;
import com.adeptus.management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public List<TeacherResponse> getAllTeachers() {
        return teacherRepository.getAllActiveTeachers()
                .stream()
                .map(teacher -> {
                    TeacherResponse response = teacherMapper.toTeacherResponse(teacher);
                    response.setSalaries(teacherMapper.toSalaryResponses(teacher.getSalaries()));
                    response.setClasses(teacherMapper.toClassResponses(teacher.getClasses()));
                    return response;
                })
                .toList();
    }

    @Override
    public Page<TeacherResponse> getAllTeachersWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return teacherRepository.getAllActiveTeachers(pageable)
                .map(teacher -> {
                    TeacherResponse response = teacherMapper.toTeacherResponse(teacher);
                    response.setSalaries(teacherMapper.toSalaryResponses(teacher.getSalaries()));
                    response.setClasses(teacherMapper.toClassResponses(teacher.getClasses()));
                    return response;
                });
    }

    @Override
    public TeacherResponse getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findActiveTeacherById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher", id));

        TeacherResponse response = teacherMapper.toTeacherResponse(teacher);
        response.setSalaries(teacherMapper.toSalaryResponses(teacher.getSalaries()));
        response.setClasses(teacherMapper.toClassResponses(teacher.getClasses()));

        return response;
    }

    @Override
    @Transactional
    public TeacherResponse createTeacher(CreateTeacherRequest request) {
        Teacher teacher = teacherMapper.toTeacher(request);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.toTeacherResponse(savedTeacher);
    }

    @Override
    @Transactional
    public TeacherResponse updateTeacher(Long id, UpdateTeacherRequest request) {
        Teacher existingTeacher = teacherRepository.findActiveTeacherById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher", id));
        teacherMapper.updateTeacherFromRequest(request, existingTeacher);
        Teacher updatedTeacher = teacherRepository.save(existingTeacher);
        return teacherMapper.toTeacherResponse(updatedTeacher);
    }

    @Override
    @Transactional
    public void deleteTeacher(Long id) {
        Teacher teacher = teacherRepository.findActiveTeacherById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher", id));
        teacher.setIsActive(false);
        teacherRepository.save(teacher);
    }
}
