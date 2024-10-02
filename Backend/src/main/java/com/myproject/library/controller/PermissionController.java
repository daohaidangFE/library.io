package com.myproject.library.controller;

import com.myproject.library.dto.request.PermissionRequest;
import com.myproject.library.dto.response.ApiResponse;
import com.myproject.library.dto.response.PermissionResponse;
import com.myproject.library.service.impl.PermissionService;
import com.myproject.library.service.impl.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/permission")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    PermissionService permissionService;
    RoleService roleService;

    @PostMapping("")
    public ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest permissionRequest) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.createPermission(permissionRequest))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<PermissionResponse>> getAllPermissions() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getAllPermissions())
                .build();
    }

    @GetMapping("/{permissionId}")
    public ApiResponse<PermissionResponse> getPermissionById(@PathVariable String permissionId) {
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.getPermission(permissionId))
                .build();
    }

    @DeleteMapping("/{permissionName}")
    public ApiResponse<Void> delete(@PathVariable String permissionName) {
        roleService.deletePermission(permissionName);
        return ApiResponse.<Void>builder().build();
    }
}
