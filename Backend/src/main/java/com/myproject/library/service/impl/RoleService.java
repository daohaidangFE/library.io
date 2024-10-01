package com.myproject.library.service.impl;

import com.myproject.library.dto.request.RoleRequest;
import com.myproject.library.dto.response.RoleResponse;
import com.myproject.library.entity.Role;
import com.myproject.library.exception.AppException;
import com.myproject.library.exception.ErrorCode;
import com.myproject.library.mapper.RoleMapper;
import com.myproject.library.repository.PermissionRepository;
import com.myproject.library.repository.RoleRepository;
import com.myproject.library.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {
    RoleRepository roleRepository;

    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    @Override
    public RoleResponse createRole(RoleRequest roleCreationRequest) {
        if (roleRepository.existsById(roleCreationRequest.getName())) {
            throw new AppException(ErrorCode.ROLE_EXISTED);
        } else {
            Role role = roleMapper.toRole(roleCreationRequest);
            role.setPermissions(new HashSet<>(permissionRepository.findAllById(roleCreationRequest.getPermissions())));
            return roleMapper.toRoleResponse(roleRepository.save(role));
        }
    }

    @Override
    public RoleResponse getRole(String roleId) {
        Role role = roleRepository.findById(roleId).orElse(null);
        if(role == null) {
            throw new AppException(ErrorCode.ROLE_NOT_EXISTED);
        } else {
            return roleMapper.toRoleResponse(role);
        }
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();

        List<RoleResponse> roleResponses = new ArrayList<>();

        for(Role role : roles) {
            roleResponses.add(roleMapper.toRoleResponse(role));
        }
        return roleResponses;
    }

    @Override
    public void deleteRole(String roleId) {
        if(!roleRepository.existsById(roleId)) {
            throw new AppException(ErrorCode.ROLE_NOT_EXISTED);
        } else {
            roleRepository.deleteById(roleId);
        }
    }


}
