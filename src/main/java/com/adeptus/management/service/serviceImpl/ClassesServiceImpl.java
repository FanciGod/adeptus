package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.request.classes.CreateClassRequest;
import com.adeptus.management.dto.request.classes.UpdateClassRequest;
import com.adeptus.management.dto.response.ClassDetailResponse;
import com.adeptus.management.entity.classes.Classes;
import com.adeptus.management.entity.classes.Course;
import com.adeptus.management.entity.staff.Staff;
import com.adeptus.management.entity.teacher.Teacher;
import com.adeptus.management.exception.EntityDuplicateException;
import com.adeptus.management.exception.EntityNotFoundException;
import com.adeptus.management.mapper.ClassesMapper;
import com.adeptus.management.repository.ClassesRepository;
import com.adeptus.management.repository.CourseRepository;
import com.adeptus.management.repository.StaffRepository;
import com.adeptus.management.repository.TeacherRepository;
import com.adeptus.management.service.ClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassesServiceImpl implements ClassesService {

    private final ClassesRepository classesRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final StaffRepository staffRepository;
    private final ClassesMapper classesMapper;

    @Override
    public List<ClassDetailResponse> getAllClasses() {
        return classesRepository.findAllActiveClasses()
                .stream()
                .map(classesMapper::toClassResponse)
                .toList();
    }

    @Override
    public ClassDetailResponse getClassById(Long id) {
        Classes classes = classesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Class", id));
        return classesMapper.toClassResponse(classes);
    }

    @Override
    @Transactional
    public ClassDetailResponse createClass(CreateClassRequest request) {
        // Kiểm tra tên lớp đã tồn tại hay chưa
        Optional<Classes> existingClass = classesRepository.findByClassName(request.getClassName());
        if (existingClass.isPresent()) {
            throw new EntityDuplicateException(request.getClassName());
        }

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new EntityNotFoundException("Course", request.getCourseId()));
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher", request.getTeacherId()));
        Staff staff = staffRepository.findById(request.getStaffId())
                .orElseThrow(() -> new EntityNotFoundException("Staff", request.getStaffId()));

        Classes classes = classesMapper.toClasses(request);
        classes.setCourse(course);
        classes.setTeacher(teacher);
        classes.setStaff(staff);

        Classes savedClass = classesRepository.save(classes);
        return classesMapper.toClassResponse(savedClass);
    }

    @Override
    @Transactional
    public ClassDetailResponse updateClass(Long id, UpdateClassRequest request) {
        Classes classes = classesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Class", id));

        Optional<Classes> existingClass = classesRepository.findByClassName(request.getClassName());
        if (existingClass.isPresent() && !existingClass.get().getId().equals(id)) {
            throw new EntityDuplicateException(request.getClassName());
        }

        classesMapper.updateClassesFromRequest(request, classes);
        Classes updatedClass = classesRepository.save(classes);
        return classesMapper.toClassResponse(updatedClass);
    }

    @Override
    @Transactional
    public void deleteClass(Long id) {
        Classes classes = classesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Class", id));
        classes.setIsActive(false);
        classesRepository.save(classes);
    }
}
