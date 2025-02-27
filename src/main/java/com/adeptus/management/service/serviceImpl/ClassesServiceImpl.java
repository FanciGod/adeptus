package com.adeptus.management.service.serviceImpl;


import com.adeptus.management.dto.StudentClassDto;
import com.adeptus.management.dto.request.CreateNewClassRequest;
import com.adeptus.management.dto.request.UpdateClassRequest;
import com.adeptus.management.dto.response.ClassesResponse;
import com.adeptus.management.entity.BaseEntity;
import com.adeptus.management.entity.Classes;
import com.adeptus.management.exception.AppException;
import com.adeptus.management.exception.ErrorCode;
import com.adeptus.management.mapper.*;
import com.adeptus.management.repository.ClassesRepository;
import com.adeptus.management.repository.CourseRepository;
import com.adeptus.management.repository.StaffRepository;
import com.adeptus.management.repository.TeacherRepository;
import com.adeptus.management.service.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl implements ClassesService {

    private final ClassesRepository classesRepository;
    private final TeacherRepository teacherRepository;
    private final StaffRepository staffRepository;
    private final CourseRepository courseRepository;

    private final ClassesMapper classesMapper;
    private final StudentClassMapper studentClassMapper;
    private final CourseMapper courseMapper;
    private final StaffMapper staffMapper;
    private final TeacherMapper teacherMapper;

    @Override
    public Page<ClassesResponse> findAllClassesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return classesRepository.findAllActiveClassesWithPagination(pageable).map(this::toClassesResponse);
    }

    @Override
    public ClassesResponse findClassById(Long id) {
        var classes = classesRepository.findAllActiveClassById(id).orElseThrow(() -> new AppException(ErrorCode.CLASS_ID_NOT_FOUND));
        return toClassesResponse(classes);
    }

    @Override
    public ClassesResponse createNewClass(CreateNewClassRequest request) {
        if(classesRepository.existsByClassName(request.getClassName())){
            throw new AppException(ErrorCode.CLASS_NAME_DUPLICATED);
        }
        var classes = classesMapper.toClasses(request);
        classes.setTeacher(teacherRepository.findByIdAndIsActiveTrue(request.getTeacherId()).orElseThrow(() -> new AppException(ErrorCode.TEACHER_ID_NOT_FOUND)));
        classes.setStudentClasses(new ArrayList<>());
        classes.setCourse(courseRepository.findByIdAndIsActiveTrue(request.getCourseId()).orElseThrow(() -> new AppException(ErrorCode.COURSE_ID_NOT_FOUND)));
        classes.setStaff(staffRepository.findActiveStaffById(request.getStaffId()).orElseThrow(() -> new AppException(ErrorCode.STAFF_ID_NOT_FOUND)));
        return toClassesResponse(classesRepository.save(classes));
    }

    @Override
    public ClassesResponse updateClassById(Long id, UpdateClassRequest request) {
        if(classesRepository.existsByClassName(request.getClassName())){
            throw new AppException(ErrorCode.CLASS_NAME_DUPLICATED);
        }
        var classes = classesRepository.findAllActiveClassById(id).orElseThrow(() -> new AppException(ErrorCode.CLASS_ID_NOT_FOUND));
        classesMapper.updateClasses(request, classes);
        return toClassesResponse(classesRepository.save(classes));
    }

    @Override
    public void deleteClassById(Long id) {
        var classes = classesRepository.findAllActiveClassById(id).orElseThrow(() -> new AppException(ErrorCode.CLASS_ID_NOT_FOUND));
        classes.setIsActive(false);
        classesRepository.save(classes);
    }

    private ClassesResponse toClassesResponse(Classes classes) {
        var classesResponse = classesMapper.toClassesResponse(classes);
        classesResponse.setStudentClassesDto(classes.getStudentClasses().stream().filter(BaseEntity::getIsActive).map(studentClassMapper::toStudentClassDto).toList());

        classesResponse.setCourseDto(courseMapper.toCourseDto(courseRepository.findByIdAndIsActiveTrue(classes.getCourse().getId()).orElse(null)));
        classesResponse.setStaffDto(staffMapper.toStaffDto(staffRepository.findActiveStaffById(classes.getStaff().getId()).orElse(null)));
        classesResponse.setTeacherDto(teacherMapper.toTeacherDto(teacherRepository.findByIdAndIsActiveTrue(classes.getTeacher().getId()).orElse(null)));
        return classesResponse;
    }

}