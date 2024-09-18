package com.myproject.library.controller;


import com.myproject.library.dto.request.UserCreationRequest;
import com.myproject.library.dto.request.UserUpdateRequest;
import com.myproject.library.dto.response.UserResponse;
import com.myproject.library.entity.User;
import com.myproject.library.dto.response.ApiResponse;
import com.myproject.library.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    UserService userService;

    @PostMapping("")
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userCreationRequest));

        return apiResponse;
    }

    @GetMapping("")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        var authetication = SecurityContextHolder.getContext().getAuthentication();

        log.info("usrname : {}", authetication.getName());
        log.info("role : {}", authetication.getAuthorities());

        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getAllUsers());
        return apiResponse;

    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable Long userId) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUser(userId));
        return apiResponse;
    }

    @PutMapping("/{userId}")
    public ApiResponse<User> updateUser(@PathVariable Long userId,@RequestBody @Valid UserUpdateRequest userUpdateRequest) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.updateUser(userId, userUpdateRequest));
        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ApiResponse.<String>builder().code(1000).result("User has been deleted").build();
    }
}
