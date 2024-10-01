package com.myproject.library.controller;


import com.myproject.library.dto.request.RoleRequest;
import com.myproject.library.dto.response.ApiResponse;
import com.myproject.library.dto.response.RoleResponse;
import com.myproject.library.service.impl.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping("")
    public ApiResponse<RoleResponse> createRole(@RequestBody  RoleRequest roleCreationRequest) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(roleCreationRequest))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<RoleResponse>> getAllRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAllRoles())
                .build();
    }

    @GetMapping("/{roleId}")
    public ApiResponse<RoleResponse> getRoleById(@PathVariable String roleId) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.getRole(roleId))
                .build();
    }

    @DeleteMapping("/{roleId}")
    public ApiResponse<Void> deleteRole(@PathVariable String roleId) {
        roleService.deleteRole(roleId);
        return ApiResponse.<Void>builder()
                .build();
    }
}
