package com.myproject.library.mapper;

import com.myproject.library.dto.request.PermissionCreationRequest;
import com.myproject.library.dto.response.PermissionResponse;
import com.myproject.library.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionCreationRequest permissionCreationRequest);
    PermissionResponse toPermissionResponse(Permission permission);
}
