package com.myproject.library.service;

import com.myproject.library.dto.request.UserCreateRequest;
import com.myproject.library.entity.User;
import com.myproject.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;


    public User createUser(UserCreateRequest userCreateRequest) {

        User user = new User();

        user.setName(userCreateRequest.getName());
        user.setAddress(userCreateRequest.getAddress());
        user.setPhone(userCreateRequest.getPhone());

        return userRepository.save(user);
    }

}
