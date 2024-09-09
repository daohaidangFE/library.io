package com.myproject.library.controller;


import com.myproject.library.dto.request.UserCreateRequest;
import com.myproject.library.dto.request.UserGetRequest;
import com.myproject.library.entity.User;
import com.myproject.library.dto.response.ApiResponse;
import com.myproject.library.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userCreateRequest));

        return apiResponse;
    }

    @GetMapping("")
    public ResponseEntity<List<UserGetRequest>> getAllUsers() {
        List<UserGetRequest> userGetRequests = userService.getAllUsers();
        return ResponseEntity.ok(userGetRequests);

    }
}
