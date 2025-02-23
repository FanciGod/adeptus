package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.ApiResponse;
import com.adeptus.management.dto.request.student.AddStudentToClassRequest;
import com.adeptus.management.dto.request.student.CreateStudentRequest;
import com.adeptus.management.dto.request.student.UpdateStudentRequest;
import com.adeptus.management.dto.response.StudentResponse;
import com.adeptus.management.entity.classes.Classes;
import com.adeptus.management.entity.student.Student;
import com.adeptus.management.entity.student.StudentClass;
import com.adeptus.management.exception.EntityDeletedException;
import com.adeptus.management.exception.EntityDuplicateException;
import com.adeptus.management.exception.EntityNotFoundException;
import com.adeptus.management.mapper.StudentMapper;
import com.adeptus.management.repository.ClassesRepository;
import com.adeptus.management.repository.StudentClassRepository;
import com.adeptus.management.repository.StudentRepository;
import com.adeptus.management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final ClassesRepository classesRepository;
    private final StudentClassRepository studentClassRepository;

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAllByIsActiveTrue()
                .stream()
                .map(studentMapper::toStudentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));
        return studentMapper.toStudentResponse(student);
    }

    @Override
    public StudentResponse createStudent(CreateStudentRequest request) {
        Optional<Student> existingStudentByEmail = studentRepository.findByEmail(request.getEmail());
        Optional<Student> existingStudentByPhone = studentRepository.findByPhone(request.getPhone());

        // Nếu student đã tồn tại nhưng bị xóa (isActive = false), ném ngoại lệ mới
        if (existingStudentByEmail.isPresent() && !existingStudentByEmail.get().getIsActive()) {
            throw new EntityDeletedException(" Email " + request.getEmail() + "already exists end deleted!");
        }

        if (existingStudentByPhone.isPresent() && !existingStudentByPhone.get().getIsActive()) {
            throw new EntityDeletedException("With email number " + request.getPhone() +"already exists end deleted!");
        }

        // Kiểm tra trùng lặp với các học viên đang hoạt động
        if (studentRepository.existsByEmailAndIsActiveTrue(request.getEmail())) {
            throw new EntityDuplicateException("Email " + request.getEmail() + " đã tồn tại.");
        }

        if (studentRepository.existsByPhoneAndIsActiveTrue(request.getPhone())) {
            throw new EntityDuplicateException("Số điện thoại " + request.getPhone() + " đã tồn tại.");
        }

        // Tạo mới học viên nếu không trùng lặp
        Student student = studentMapper.toStudent(request);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponse(savedStudent);
    }


    @Override
    public StudentResponse updateStudent(Long id, UpdateStudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));

        // Kiểm tra trùng email
        if (!student.getEmail().equals(request.getEmail()) && studentRepository.existsByEmailAndIsActiveTrue(request.getEmail())) {
            throw new EntityDuplicateException("Email " + request.getEmail() + " đã tồn tại.");
        }

        // Kiểm tra trùng số điện thoại
        if (!student.getPhone().equals(request.getPhone()) && studentRepository.existsByPhoneAndIsActiveTrue(request.getPhone())) {
            throw new EntityDuplicateException("Số điện thoại " + request.getPhone() + " đã tồn tại.");
        }

        studentMapper.updateStudentFromRequest(request, student);
        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponse(updatedStudent);
    }

    @Override
    public ApiResponse<String> deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));
        student.setIsActive(false);
        studentRepository.save(student);
        return ApiResponse.<String>builder()
                .code(200)
                .message("Student deleted successfully.")
                .result("Success")
                .build();
    }
    @Override
    @Transactional
    public ApiResponse<String> addStudentToClass(AddStudentToClassRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("Student", request.getStudentId()));

        Classes classes = classesRepository.findById(request.getClassId())
                .orElseThrow(() -> new EntityNotFoundException("Class", request.getClassId()));

        // Kiểm tra xem học viên đã được thêm vào lớp chưa
        boolean exists = studentClassRepository.existsByStudentAndClasses(student, classes);
        if (exists) {
            throw new EntityDuplicateException("Student already enrolled in this class");
        }

        StudentClass studentClass = new StudentClass();
        studentClass.setStudent(student);
        studentClass.setClasses(classes);
        studentClass.setLessonRemain(request.getLessonRemain());
        studentClass.setTotalPaid(request.getTotalPaid());

        studentClassRepository.save(studentClass);

        return ApiResponse.<String>builder()
                .message("Student successfully enrolled in the class.")
                .result("Enrollment successful")
                .build();
    }
    @Override
    @Transactional
    public ApiResponse<String> removeStudentFromClass(Long studentId, Long classId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student", studentId));

        Classes classes = classesRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("Class", classId));

        // Kiểm tra xem học viên có trong lớp học không
        boolean exists = studentClassRepository.existsByStudentAndClasses(student, classes);
        if (!exists) {
            throw new EntityNotFoundException("Student is not enrolled in this class");
        }

        // Xóa bản ghi khỏi bảng StudentClass
        studentClassRepository.deleteByStudentIdAndClassesId(studentId, classId);

        return ApiResponse.<String>builder()
                .message("Student successfully removed from the class.")
                .result("Removal successful")
                .build();
    }
}
