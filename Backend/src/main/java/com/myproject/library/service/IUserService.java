package com.myproject.library.service;

import com.myproject.library.dto.request.user.UserCreationRequest;
import com.myproject.library.dto.request.user.UserUpdateRequest;
import com.myproject.library.dto.response.user.UserResponse;
import com.myproject.library.entity.User;

import java.util.List;

public interface IUserService {

    UserResponse createUser(UserCreationRequest userCreationRequest);

    UserResponse getUser(Long id);

    UserResponse updateUser(Long id, UserUpdateRequest userUpdateRequest);

    List<UserResponse> getAllUsers();

    void deleteUser(Long id);
}
