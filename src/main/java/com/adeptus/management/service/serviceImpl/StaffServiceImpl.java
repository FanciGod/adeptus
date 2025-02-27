package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.ClassesDto;
import com.adeptus.management.dto.request.CreateNewStaffRequest;
import com.adeptus.management.dto.request.UpdateStaffBasicInfoRequest;
import com.adeptus.management.dto.response.StaffResponse;
import com.adeptus.management.entity.BaseEntity;
import com.adeptus.management.entity.Classes;
import com.adeptus.management.entity.Staff;
import com.adeptus.management.entity.StaffSalary;
import com.adeptus.management.exception.AppException;
import com.adeptus.management.exception.ErrorCode;
import com.adeptus.management.mapper.ClassesMapper;
import com.adeptus.management.mapper.RoleMapper;
import com.adeptus.management.mapper.StaffMapper;
import com.adeptus.management.mapper.StaffSalaryMapper;
import com.adeptus.management.repository.ClassesRepository;
import com.adeptus.management.repository.RoleRepository;
import com.adeptus.management.repository.StaffRepository;
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
import java.util.ArrayList;
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
    private final ClassesRepository classesRepository;

    private final PasswordEncoder passwordEncoder;
    private final Cloudinary cloudinary;

    private final StaffMapper staffMapper;
    private final StaffSalaryMapper staffSalaryMapper;
    private final RoleMapper roleMapper;
    private final ClassesMapper classesMapper;


    private List<Staff> getAllStaffs() {
        return staffRepository.getAllStaffs();
    }

    @Override
    public Page<StaffResponse> getAllStaffWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return staffRepository.getAllStaffsWithPagination(pageable).map(this::toStaffResponse);
    }

    @Override
    public StaffResponse getStaffById(Long id){
        var staff = staffRepository.findActiveStaffById(id).orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND));
        return toStaffResponse(staff);
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

        staff.setSalaries(new ArrayList<>());
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
    public StaffResponse updateStaffBasicInfoById(UpdateStaffBasicInfoRequest request, Long id){
        var staff = staffRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND));
       staffMapper.updateStaff(request,staff);
        return toStaffResponse(staffRepository.save(staff));
    }

    @Override
    public void deleteStaffById(Long id){
        var staff = staffRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND));
        staff.setIsActive(false);
        staffRepository.save(staff);
    }





    private StaffResponse toStaffResponse(Staff staff) {
        var staffResponse = staffMapper.toStaffResponse(staff);
        if (staff.getSalaries() != null) {
            staffResponse.setSalaries(staff.getSalaries().stream().map(staffSalaryMapper::toStaffSalaryDto).toList());
        }

        staffResponse.setRoles(staff.getRoles().stream().filter(BaseEntity::getIsActive).map(roleMapper::toRoleDto).toList());
        staffResponse.setClasses(staff.getClasses().stream().filter(BaseEntity::getIsActive).map(this::toClassesDto).collect(Collectors.toSet()));
        staffResponse.setCreatedAt(staff.getCreatedAt());
        staffResponse.setUpdatedAt(staff.getUpdatedAt());
        staffResponse.setIsActive(staff.getIsActive());
        return staffResponse;
    }

    private ClassesDto toClassesDto(Classes classes){
        ClassesDto classesDto = classesMapper.toClassesDto(classes);
        classesDto.setTeacherName(classes.getTeacher().getName());
        return classesDto;
    }

    private void uploadThumbnail(Staff staff, MultipartFile file) throws IOException {
        if (file == null) {
            throw new AppException(ErrorCode.IMAGE_UPLOAD_FAILED);
        }
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = uploadResult.get("url").toString();
        String publicId = uploadResult.get("public_id").toString();
        staff.setThumbnailUrl(url);
        staff.setThumbnailPublicId(publicId);
    }




}
