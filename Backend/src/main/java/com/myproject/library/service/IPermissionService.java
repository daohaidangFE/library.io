package com.myproject.library.service;

import com.myproject.library.dto.request.PermissionRequest;
import com.myproject.library.dto.response.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    PermissionResponse createPermission(PermissionRequest permissionRequest);

    List<PermissionResponse> getAllPermissions();

    PermissionResponse getPermission(String permissionId);

    PermissionResponse updatePermission(String permissionId, PermissionRequest permissionUpdateRequest);

    void deletePermission(String name);
}
