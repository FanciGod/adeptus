package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.request.attendance.AttendanceRequest;
import com.adeptus.management.dto.response.AttendanceResponse;
import com.adeptus.management.entity.attendance.ClassHistory;
import com.adeptus.management.entity.classes.Classes;
import com.adeptus.management.entity.student.Student;
import com.adeptus.management.entity.student.StudentClass;
import com.adeptus.management.entity.teacher.Teacher;
import com.adeptus.management.exception.EntityNotFoundException;
import com.adeptus.management.exception.StudentOutOfLessonsException;
import com.adeptus.management.mapper.AttendanceMapper;
import com.adeptus.management.repository.*;
import com.adeptus.management.service.IAttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements IAttendanceService {

    private final ClassHistoryRepository classHistoryRepository;
    private final StudentRepository studentRepository;
    private final ClassesRepository classesRepository;
    private final TeacherRepository teacherRepository;
    private final StudentClassRepository studentClassRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    @Transactional
    public AttendanceResponse markAttendance(@Valid AttendanceRequest request) {
        Classes classes = classesRepository.findById(request.getClassId())
                .orElseThrow(() -> new EntityNotFoundException("Class", request.getClassId()));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher", request.getTeacherId()));

        // Kiểm tra học viên có tồn tại không
        Set<Student> students = new HashSet<>(studentRepository.findAllById(request.getStudentIds()));
        if (students.isEmpty() || students.size() != request.getStudentIds().size()) {
            throw new EntityNotFoundException("Một hoặc nhiều học viên không tồn tại.");
        }

        // Kiểm tra học viên có thuộc lớp học không
        for (Student student : students) {
            Optional<StudentClass> studentClassOpt = studentClassRepository.findByStudentAndClasses(student, classes);
            if (studentClassOpt.isEmpty()) {
                throw new EntityNotFoundException("Học viên " + student.getId() + " không thuộc lớp học này.");
            }
        }

        // Tạo lịch sử điểm danh
        ClassHistory classHistory = new ClassHistory();
        classHistory.setClasses(classes);
        classHistory.setTeacher(teacher);
        classHistory.setStudents(students);
        classHistory.setDate(LocalDateTime.now());

        classHistoryRepository.save(classHistory);

        // Cập nhật số buổi học còn lại của học viên
        for (Student student : students) {
            StudentClass studentClass = studentClassRepository.findByStudentAndClasses(student, classes)
                    .orElseThrow(() -> new EntityNotFoundException("StudentClass không tồn tại"));
            if (studentClass.getLessonRemain() > 0) {
                studentClass.setLessonRemain(studentClass.getLessonRemain() - 1);
                studentClassRepository.save(studentClass);
            } else {
                throw new StudentOutOfLessonsException(student.getId());
            }
        }

        return attendanceMapper.toResponse(classHistory);
    }

    @Override
    public List<AttendanceResponse> getAttendanceByClass(Long classId) {
        List<ClassHistory> classHistories = classHistoryRepository.findByClassesId(classId);
        if (classHistories.isEmpty()) {
            throw new EntityNotFoundException("Không có lịch sử điểm danh cho lớp ID " + classId);
        }
        return classHistories.stream().map(attendanceMapper::toResponse).toList();
    }
}

