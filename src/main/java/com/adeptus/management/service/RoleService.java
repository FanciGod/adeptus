package com.adeptus.management.service;

import com.adeptus.management.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAllRoles();
}
