package com.myproject.library.service;

import com.myproject.library.dto.request.UserCreateRequest;
import com.myproject.library.dto.request.UserGetRequest;
import com.myproject.library.entity.User;
import com.myproject.library.exception.AppException;
import com.myproject.library.exception.ErrorCode;
import com.myproject.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



    public User createUser(UserCreateRequest userCreateRequest) {

        if(userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = User.builder()
                .username(userCreateRequest.getUsername())
                .password(userCreateRequest.getPassword())
                .email(userCreateRequest.getEmail())
                .address(userCreateRequest.getAddress())
                .phone(userCreateRequest.getPhone())
                .Dob(LocalDate.now())
                .build();

        return userRepository.save(user);
    }


    public List<UserGetRequest> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserGetRequest> userGetRequests = new ArrayList<>();
        for(User user : users) {
            UserGetRequest userGetRequest = UserGetRequest.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .phone(user.getPhone())
                    .Dob(user.getDob())
                    .build();

            userGetRequests.add(userGetRequest);
        }
        return userGetRequests;
    }


}
