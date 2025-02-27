package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.request.CreateNewCourseRequest;
import com.adeptus.management.dto.request.UpdateCourseRequest;
import com.adeptus.management.dto.response.CourseResponse;
import com.adeptus.management.entity.Course;
import com.adeptus.management.exception.AppException;
import com.adeptus.management.exception.ErrorCode;
import com.adeptus.management.mapper.CourseMapper;
import com.adeptus.management.repository.CourseRepository;
import com.adeptus.management.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    private final CourseMapper courseMapper;

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findByIsActiveTrue().stream().map(this::toCourseResponse).toList();
    }

    @Override
    public CourseResponse createNewCourse(CreateNewCourseRequest request) {
        var course = courseMapper.toCourse(request);
        return toCourseResponse(courseRepository.save(course));
    }

    @Override
    public CourseResponse updateCourseById(UpdateCourseRequest request, Long id) {
        var course = courseRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new AppException(ErrorCode.COURSE_ID_NOT_FOUND));
        courseMapper.updateCourse(request, course);
        return toCourseResponse(courseRepository.save(course));
    }

    @Override
    public void deleteCourseById(Long id) {
        var course = courseRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new AppException(ErrorCode.COURSE_ID_NOT_FOUND));
        course.setIsActive(false);
        courseRepository.save(course);
    }

    private CourseResponse toCourseResponse(Course course) {
        return courseMapper.toCourseResponse(course);
    }


}
