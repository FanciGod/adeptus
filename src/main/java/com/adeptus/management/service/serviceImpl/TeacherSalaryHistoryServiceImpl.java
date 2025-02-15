package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.request.teacher.TeacherSalaryHistoryCreateRequest;
import com.adeptus.management.dto.request.teacher.TeacherSalaryHistoryUpdateRequest;
import com.adeptus.management.dto.response.TeacherSalaryHistoryResponse;
import com.adeptus.management.entity.teacher.Teacher;
import com.adeptus.management.entity.teacher.TeacherSalaryHistory;
import com.adeptus.management.exception.EntityNotFoundException;
import com.adeptus.management.mapper.TeacherSalaryHistoryMapper;
import com.adeptus.management.repository.TeacherRepository;
import com.adeptus.management.repository.TeacherSalaryHistoryRepository;
import com.adeptus.management.service.ITeacherSalaryHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherSalaryHistoryServiceImpl implements ITeacherSalaryHistoryService {

    private final TeacherSalaryHistoryRepository repository;
    private final TeacherRepository teacherRepository;
    private final TeacherSalaryHistoryMapper mapper;

    @Override
    public TeacherSalaryHistoryResponse getById(Long id) {
        TeacherSalaryHistory entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TeacherSalaryHistory", id));
        return mapper.toResponse(entity);
    }

    @Override
    public List<TeacherSalaryHistoryResponse> getByTeacherId(Long teacherId) {
        List<TeacherSalaryHistory> salaryHistories = repository.findByTeacherId(teacherId);
        return salaryHistories.stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<TeacherSalaryHistoryResponse> getAll() {
        List<TeacherSalaryHistory> salaryHistories = repository.findAll();
        return salaryHistories.stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public TeacherSalaryHistoryResponse create(TeacherSalaryHistoryCreateRequest request) {
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher", request.getTeacherId()));

        TeacherSalaryHistory entity = mapper.toEntity(request);
        entity.setTeacher(teacher);

        TeacherSalaryHistory savedEntity = repository.save(entity);
        return mapper.toResponse(savedEntity);
    }

    @Override
    public TeacherSalaryHistoryResponse update(Long id, TeacherSalaryHistoryUpdateRequest request) {
        TeacherSalaryHistory existingEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("TeacherSalaryHistory", id));

        mapper.updateEntity(existingEntity, request);
        TeacherSalaryHistory updatedEntity = repository.save(existingEntity);
        return mapper.toResponse(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("TeacherSalaryHistory", id);
        }
        repository.deleteById(id);
    }
}
