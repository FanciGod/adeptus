package com.adeptus.management.mapper;

import com.adeptus.management.dto.request.classes.CourseRequest;
import com.adeptus.management.dto.response.CourseResponse;
import com.adeptus.management.entity.classes.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "isActive", source = "isActive")
    CourseResponse toCourseResponse(Course course);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    Course toCourse(CourseRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    void updateCourseFromRequest(CourseRequest request, @MappingTarget Course course);
}
