package com.myproject.library.service;


import com.myproject.library.dto.request.RoleRequest;
import com.myproject.library.dto.response.RoleRespone;
import com.myproject.library.entity.Role;
import com.myproject.library.mapper.RoleMapper;
import com.myproject.library.repository.PermissionRepository;
import com.myproject.library.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    public RoleRespone create(RoleRequest roleRequest) {
        var role = roleMapper.toRole(roleRequest);
        var permissions = permissionRepository.findAllById(roleRequest.getPermissions());
        role.setPermissions(new HashSet<>(permissions));
        role = roleRepository.save(role);
        return roleMapper.toRoleRespone(role);
    }

    public List<RoleRespone> getAll() {
        var roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toRoleRespone).toList();
    }

    public void delete(String roleName) {
        roleRepository.deleteById(roleName);
    }
}
