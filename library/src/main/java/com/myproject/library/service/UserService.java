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
        User user = new User();

        if(userRepository.existsByUsername(userCreateRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        user.setUsername(userCreateRequest.getUsername());
        user.setPassword(userCreateRequest.getPassword());
        user.setEmail(userCreateRequest.getEmail());
        user.setAddress(userCreateRequest.getAddress());
        user.setPhone(userCreateRequest.getPhone());
        user.setDob(LocalDate.now());
        return userRepository.save(user);
    }


    public List<UserGetRequest> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserGetRequest> userGetRequests = new ArrayList<>();
        for(User user : users) {
            UserGetRequest userGetRequest = new UserGetRequest();
            userGetRequest.setId(user.getId());
            userGetRequest.setUsername(user.getUsername());
            userGetRequest.setEmail(user.getEmail());
            userGetRequest.setAddress(user.getAddress());
            userGetRequest.setPhone(user.getPhone());
            userGetRequest.setDob(user.getDob());
            userGetRequests.add(userGetRequest);
        }
        return userGetRequests;
    }


}
