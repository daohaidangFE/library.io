package com.myproject.library.controller;


import com.myproject.library.dto.request.UserCreateRequest;
import com.myproject.library.entity.User;
import com.myproject.library.response.ApiResponse;
import com.myproject.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    ApiResponse<User> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(userCreateRequest));

        return apiResponse;
    }
}
