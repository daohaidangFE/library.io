package com.myproject.library.service;

import com.myproject.library.dto.request.PermissionCreationRequest;
import com.myproject.library.dto.response.PermissionResponse;
import com.myproject.library.entity.Permission;

import java.util.List;

public interface IPermissionService {
    PermissionResponse createPermission(PermissionCreationRequest permissionCreationRequest);

    List<PermissionResponse> getAllPermissions();

    void deletePermission(String name);
}
