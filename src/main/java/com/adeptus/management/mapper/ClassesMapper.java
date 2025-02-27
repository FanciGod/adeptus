package com.adeptus.management.mapper;

import com.adeptus.management.dto.ClassesDto;
import com.adeptus.management.dto.request.CreateNewClassRequest;
import com.adeptus.management.dto.request.UpdateClassRequest;
import com.adeptus.management.dto.request.UpdateStaffBasicInfoRequest;
import com.adeptus.management.dto.response.ClassesResponse;
import com.adeptus.management.entity.Classes;
import com.adeptus.management.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClassesMapper {
    ClassesDto toClassesDto (Classes classes);
    ClassesResponse toClassesResponse(Classes classes);

    Classes toClasses(CreateNewClassRequest request);
    void updateClasses(UpdateClassRequest request, @MappingTarget Classes classes);
}
