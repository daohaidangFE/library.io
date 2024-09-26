package com.myproject.library.service.impl;

import com.myproject.library.dto.request.user.UserCreationRequest;
import com.myproject.library.dto.request.user.UserUpdateRequest;
import com.myproject.library.dto.response.user.UserResponse;
import com.myproject.library.entity.User;
import com.myproject.library.exception.AppException;
import com.myproject.library.exception.ErrorCode;
import com.myproject.library.mapper.UserMapper;
import com.myproject.library.repository.UserRepository;
import com.myproject.library.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserResponse createUser(UserCreationRequest userCreationRequest) {

        if(userRepository.existsByUsername(userCreationRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(userCreationRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        } else {
            UserResponse userResponse = userMapper.toUserResponse(user);
            return userResponse;
        }
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElse(null);

        if(user == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        } else {
            userMapper.updateUser(user, userUpdateRequest);
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
            UserResponse userResponse =  userMapper.toUserResponse(userRepository.save(user));
            return userResponse;
        }

    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponse> userResponses = new ArrayList<>();
        for(User user : users) {
            UserResponse userResponse = userMapper.toUserResponse(user);

            userResponses.add(userResponse);
        }
        return userResponses;
    }

    @Override
    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        } else {
            userRepository.deleteById(id);
        }
    }

}
