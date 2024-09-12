package com.myproject.library.service;

import com.myproject.library.dto.request.UserCreateRequest;
import com.myproject.library.dto.request.UserUpdateRequest;
import com.myproject.library.dto.response.UserResponse;
import com.myproject.library.entity.User;
import com.myproject.library.exception.AppException;
import com.myproject.library.exception.ErrorCode;
import com.myproject.library.mapper.UserMapper;
import com.myproject.library.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User createUser(UserCreateRequest userCreateRequest) {

        if(userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(userCreateRequest);
        user.setDob(userCreateRequest.getDob());


        return userRepository.save(user);
    }

    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        } else {
            UserResponse userResponse = userMapper.toUserResponse(user);
            userResponse.setDob(user.getDob());
            return userResponse;
        }
    }

    public UserResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id).orElse(null);

        if(user == null) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        } else {
            userMapper.updateUser(user, userUpdateRequest);
            UserResponse userResponse =  userMapper.toUserResponse(userRepository.save(user));
            userResponse.setDob(user.getDob());
            return userResponse;
        }

    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponse> userResponses = new ArrayList<>();
        for(User user : users) {
            UserResponse userResponse = UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .phone(user.getPhone())
                    .Dob(user.getDob())
                    .build();

            userResponses.add(userResponse);
        }
        return userResponses;
    }

    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        } else {
            userRepository.deleteById(id);
        }
    }

}
