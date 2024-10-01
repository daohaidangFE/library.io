package com.myproject.library.service.impl;


import com.myproject.library.dto.request.PermissionRequest;
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
    public PermissionResponse createPermission(PermissionRequest permissionRequest) {
        if(permissionRepository.existsById(permissionRequest.getName())) {
            throw new AppException(ErrorCode.PERMISSION_EXISTED);
        } else {
            Permission permission = permissionMapper.toPermission(permissionRequest);
            return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
        }
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
    public PermissionResponse getPermission(String permissionId) {
        Permission permission = permissionRepository.findById(permissionId).orElse(null);
        if(permission == null) {
            throw  new AppException(ErrorCode.PERMISSION_NOT_EXISTED);
        } else {
            return permissionMapper.toPermissionResponse(permission);
        }
    }

    @Override
    public PermissionResponse updatePermission(String permissionId, PermissionRequest permissionUpdateRequest) {
        Permission permission = permissionRepository.findById(permissionId).orElse(null);
        if(permission == null) {
            throw  new AppException(ErrorCode.PERMISSION_NOT_EXISTED);
        } else {
            permissionMapper.updatePermission(permission, permissionUpdateRequest);
            return permissionMapper.toPermissionResponse(permission);
        }
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
