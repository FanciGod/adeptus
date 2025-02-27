package com.adeptus.management.mapper;

import com.adeptus.management.dto.CourseDto;
import com.adeptus.management.dto.request.CreateNewCourseRequest;
import com.adeptus.management.dto.request.UpdateCourseRequest;
import com.adeptus.management.dto.response.CourseResponse;
import com.adeptus.management.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toCourse(CreateNewCourseRequest request);
    CourseDto toCourseDto(Course course);

    CourseResponse toCourseResponse(Course course);

    void updateCourse(UpdateCourseRequest request, @MappingTarget Course course);
}
