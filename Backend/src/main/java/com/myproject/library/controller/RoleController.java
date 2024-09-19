package com.myproject.library.controller;

import com.myproject.library.dto.request.RoleRequest;
import com.myproject.library.dto.response.ApiResponse;
import com.myproject.library.dto.response.RoleRespone;
import com.myproject.library.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping("")
    ApiResponse<RoleRespone> createRole(@RequestBody RoleRequest roleRequest) {
        return ApiResponse.<RoleRespone>builder()
                .result(roleService.create(roleRequest))
                .build();
    }

    @GetMapping("")
    ApiResponse<List<RoleRespone>> getAll() {
        return ApiResponse.<List<RoleRespone>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> deleteRole(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }
}
