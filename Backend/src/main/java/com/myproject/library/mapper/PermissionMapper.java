package com.myproject.library.mapper;

import com.myproject.library.dto.request.PermissionRequest;
import com.myproject.library.dto.response.PermissionResponse;
import com.myproject.library.entity.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);
    PermissionResponse toPermissionResponse(Permission permission);

    void updatePermission(@MappingTarget  Permission permission, PermissionRequest permissionUpdateRequest);

}
