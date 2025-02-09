package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.request.staff.CreateNewStaffRequest;
import com.adeptus.management.dto.request.staff.UpdateStaffRequest;
import com.adeptus.management.dto.response.StaffResponse;
import com.adeptus.management.entity.staff.Staff;
import com.adeptus.management.entity.staff.StaffSalary;
import com.adeptus.management.exception.AppException;
import com.adeptus.management.exception.ErrorCode;
import com.adeptus.management.mapper.ClassesMapper;
import com.adeptus.management.mapper.RoleMapper;
import com.adeptus.management.mapper.StaffMapper;
import com.adeptus.management.mapper.StaffSalaryMapper;
import com.adeptus.management.repository.ClassesRepository;
import com.adeptus.management.repository.RoleRepository;
import com.adeptus.management.repository.StaffRepository;
import com.adeptus.management.repository.StaffSalaryRepository;
import com.adeptus.management.service.StaffService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;
    private final StaffSalaryRepository staffSalaryRepository;
    private final ClassesRepository classesRepository;

    private final PasswordEncoder passwordEncoder;
    private final Cloudinary cloudinary;

    private final StaffMapper staffMapper;
    private final StaffSalaryMapper staffSalaryMapper;
    private final RoleMapper roleMapper;
    private final ClassesMapper classesMapper;

    public List<StaffResponse> getAllStaffs() {
        return staffRepository.getAllStaffs()
                .stream()
                .map(staffMapper::toStaffResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<StaffResponse> getAllStaffWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return staffRepository.getAllStaffsWithPagination(pageable).map(this::toStaffResponse);
    }

    @Override
    @Transactional
    public StaffResponse createNewStaff(CreateNewStaffRequest request) throws IOException {
        log.warn(request.getPassword());
        if (request.getDob() == null) {
            throw new AppException(ErrorCode.INVALID_IMAGE);
        }
        if (!request.getPassword().equals(request.getRePassword())) {
            throw new AppException(ErrorCode.PASSWORD_NOT_MATCH);
        }
        if (staffRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USERNAME_DUPLICATED);
        }
        if (staffRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_DUPLICATED);
        }
        if (staffRepository.existsByPhone(request.getPhone())) {
            throw new AppException(ErrorCode.PHONE_DUPLICATED);
        }
        String password = passwordEncoder.encode(request.getPassword());

        var staff = staffMapper.toStaff(request);
        staff.setPassword(password);
        uploadThumbnail(staff, request.getThumbnail());


        staff.setRoles(request.getRoleId().stream().map(roleId -> roleRepository.findById(roleId).orElseThrow(() -> new AppException(ErrorCode.ROLE_ID_NOT_FOUND))).collect(Collectors.toSet()));
        StaffSalary staffSalary = StaffSalary.builder()
                .salary(request.getSalary())
                .staff(staff)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(30))
                .build();

        staff.setSalaries(new HashSet<>());
        staff.getSalaries().add(staffSalary);

        if (staff.getClasses() == null) {
            staff.setClasses(new HashSet<>());
        }

        if (request.getClassId() != null) {
            staff.setClasses(request.getClassId().stream().map(classId -> classesRepository.findById(classId).orElseThrow(() -> new AppException(ErrorCode.CLASS_ID_NOT_FOUND))).collect(Collectors.toSet()));

        }
        var newStaff = staffRepository.save(staff);

        return toStaffResponse(newStaff);
    }


    @Override
    @Transactional
    public StaffResponse updateStaff(Long id, UpdateStaffRequest request) throws IOException {
        // Lấy staff hiện tại, chỉ update nếu staff đang active
        Staff existingStaff = staffRepository.findById(id)
                .filter(Staff::getIsActive)
                .orElseThrow(() -> new AppException(ErrorCode.STAFF_NOT_FOUND));

        // Kiểm tra trùng email: nếu email mới khác với email hiện tại, kiểm tra trùng lặp trong database
        if (!existingStaff.getEmail().equalsIgnoreCase(request.getEmail())) {
            if (staffRepository.existsByEmail(request.getEmail())) {
                throw new AppException(ErrorCode.EMAIL_DUPLICATED);
            }
        }
        if (!existingStaff.getUsername().equalsIgnoreCase(request.getUsername())) {
            if (staffRepository.existsByUsername(request.getUsername())) {
                throw new AppException(ErrorCode.USERNAME_DUPLICATED);
            }
        }
        if (!existingStaff.getPhone().equals(request.getPhone())) {
            if (staffRepository.existsByPhone(request.getPhone())) {
                throw new AppException(ErrorCode.PHONE_DUPLICATED);
            }
        }
        // Cập nhật các trường cơ bản
        existingStaff.setUsername(request.getUsername());
        existingStaff.setFullName(request.getFullName());
        existingStaff.setEmail(request.getEmail());
        existingStaff.setDob(request.getDob());
        existingStaff.setPhone(request.getPhone());

        // Cập nhật danh sách role nếu có dữ liệu mới
        if (request.getRoleId() != null && !request.getRoleId().isEmpty()) {
            existingStaff.setRoles(
                    request.getRoleId().stream()
                            .map(roleId -> roleRepository.findById(roleId)
                                    .orElseThrow(() -> new AppException(ErrorCode.ROLE_ID_NOT_FOUND)))
                            .collect(Collectors.toSet())
            );
        }

        // Cập nhật danh sách lớp học nếu có
        if (request.getClassId() != null && !request.getClassId().isEmpty()) {
            existingStaff.setClasses(
                    request.getClassId().stream()
                            .map(classId -> classesRepository.findById(classId)
                                    .orElseThrow(() -> new AppException(ErrorCode.CLASS_ID_NOT_FOUND)))
                            .collect(Collectors.toSet())
            );
        }

        // Cập nhật salary: nếu có dữ liệu salary mới thì cập nhật
        if (request.getSalary() != null) {
            // Nếu staff đã có một hay nhiều bản ghi salary, cập nhật bản ghi đầu tiên (hoặc chọn logic phù hợp)
            if (existingStaff.getSalaries() != null && !existingStaff.getSalaries().isEmpty()) {
                StaffSalary currentSalary = existingStaff.getSalaries().iterator().next();
                currentSalary.setSalary(request.getSalary());
                currentSalary.setStartDate(LocalDate.now());
                currentSalary.setEndDate(LocalDate.now().plusYears(30));
            } else {
                // Nếu chưa có salary, tạo mới
                StaffSalary newSalary = StaffSalary.builder()
                        .salary(request.getSalary())
                        .staff(existingStaff)
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now().plusYears(30))
                        .build();
                if (existingStaff.getSalaries() == null) {
                    existingStaff.setSalaries(new HashSet<>());
                }
                existingStaff.getSalaries().add(newSalary);
            }
        }

        // Lưu lại staff đã được cập nhật
        Staff updatedStaff = staffRepository.save(existingStaff);
        return toStaffResponse(updatedStaff);
    }

    @Override
    @Transactional
    public void deleteStaff(Long id) {
        // Lấy staff hiện tại, chỉ xóa (logical delete) nếu staff đang active
        Staff existingStaff = staffRepository.findById(id)
                .filter(Staff::getIsActive)
                .orElseThrow(() -> new AppException(ErrorCode.STAFF_NOT_FOUND));

        // Logical delete: đánh dấu staff không active
        existingStaff.setIsActive(false);
        staffRepository.save(existingStaff);
    }

    private StaffResponse toStaffResponse(Staff staff) {
        var staffResponse = staffMapper.toStaffResponse(staff);
        if (staff.getSalaries() != null) {
            staffResponse.setSalaries(staff.getSalaries().stream().map(staffSalaryMapper::toStaffSalaryDto).toList());
        }

        staffResponse.setRoles(staff.getRoles().stream().map(roleMapper::toRoleDto).toList());
        staffResponse.setClasses(staff.getClasses().stream().map(classesMapper::toClassesDto).collect(Collectors.toSet()));
        staffResponse.setCreatedAt(staff.getCreatedAt());
        staffResponse.setUpdatedAt(staff.getUpdatedAt());
        staffResponse.setIsActive(staff.getIsActive());
        return staffResponse;
    }

    private void uploadThumbnail(Staff staff, MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            //throw new AppException(ErrorCode.IMAGE_UPLOAD_FAILED);
            return;
        }
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = uploadResult.get("url").toString();
        String publicId = uploadResult.get("public_id").toString();
        staff.setThumbnailUrl(url);
        staff.setThumbnailPublicId(publicId);
    }
}
