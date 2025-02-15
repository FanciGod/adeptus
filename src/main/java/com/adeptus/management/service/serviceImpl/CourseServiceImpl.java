package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.request.classes.CourseRequest;
import com.adeptus.management.dto.response.CourseResponse;
import com.adeptus.management.entity.classes.Course;
import com.adeptus.management.exception.EntityNotFoundException;
import com.adeptus.management.mapper.CourseMapper;
import com.adeptus.management.repository.CourseRepository;
import com.adeptus.management.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAllActiveCourses()
                .stream()
                .map(courseMapper::toCourseResponse)
                .toList();
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course", id));
        return courseMapper.toCourseResponse(course);
    }

    @Override
    @Transactional
    public CourseResponse createCourse(CourseRequest request) {
        Course course = courseMapper.toCourse(request);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toCourseResponse(savedCourse);
    }

    @Override
    @Transactional
    public CourseResponse updateCourse(Long id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course", id));
        courseMapper.updateCourseFromRequest(request, course);
        Course updatedCourse = courseRepository.save(course);
        return courseMapper.toCourseResponse(updatedCourse);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course", id));
        course.setIsActive(false);
        courseRepository.save(course);
    }
}
