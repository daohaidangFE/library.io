package com.myproject.library.service.impl;


import com.myproject.library.dto.request.PermissionCreationRequest;
import com.myproject.library.dto.response.PermissionResponse;
import com.myproject.library.entity.Permission;
import com.myproject.library.exception.AppException;
import com.myproject.library.exception.ErrorCode;
import com.myproject.library.mapper.PermissionMapper;
import com.myproject.library.repository.PermissionRepository;
import com.myproject.library.service.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService implements IPermissionService {
    PermissionRepository permissionRepository;

    PermissionMapper permissionMapper;


    @Override
    public PermissionResponse createPermission(PermissionCreationRequest permissionCreationRequest) {
        Permission permission = permissionMapper.toPermission(permissionCreationRequest);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    @Override
    public List<PermissionResponse> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();

        List<PermissionResponse> permissionResponses = new ArrayList<>();
        for(Permission permission : permissions) {
            PermissionResponse permissionResponse = permissionMapper.toPermissionResponse(permission);
            permissionResponses.add(permissionResponse);
        }
        return permissionResponses;
    }

    @Override
    public void deletePermission(String name) {
        if(!permissionRepository.existsById(name)) {
            throw new AppException(ErrorCode.PERMISSION_NOT_EXISTED);
        } else {
            permissionRepository.deleteById(name);
        }
    }
}
