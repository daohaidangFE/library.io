package com.myproject.library.service;

import com.myproject.library.dto.request.RoleRequest;
import com.myproject.library.dto.response.RoleResponse;

import java.util.List;

public interface IRoleService {
    RoleResponse createRole(RoleRequest roleCreationRequest);

    RoleResponse getRole(String roleId);

    List<RoleResponse> getAllRoles();

    void deleteRole(String roleId);

    void removePermissionFromRoles(String permissionId);

    void deletePermission(String permissionId);
}
