package com.adeptus.management.mapper;

import com.adeptus.management.dto.RoleDto;
import com.adeptus.management.dto.response.RoleResponse;
import com.adeptus.management.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toRoleDto(Role role);

    RoleResponse toRoleResponse(Role role);
}
