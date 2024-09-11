package com.myproject.library.mapper;

import com.myproject.library.dto.request.UserCreateRequest;
import com.myproject.library.dto.request.UserUpdateRequest;
import com.myproject.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring") // Khai bao de co the inject de dang
public interface UserMapper {
    User toUser(UserCreateRequest userCreateRequest);
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest); // mapping tu userUpdateRequest sang user
}
