package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.response.RoleResponse;
import com.adeptus.management.mapper.RoleMapper;
import com.adeptus.management.repository.RoleRepository;
import com.adeptus.management.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;
    @Override
    public List<RoleResponse> getAllRoles(){
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }



}
