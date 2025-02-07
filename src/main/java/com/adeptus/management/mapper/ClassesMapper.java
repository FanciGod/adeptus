package com.adeptus.management.mapper;

import com.adeptus.management.dto.ClassesDto;
import com.adeptus.management.entity.classes.Classes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassesMapper {
    ClassesDto toClassesDto (Classes classes);
}
