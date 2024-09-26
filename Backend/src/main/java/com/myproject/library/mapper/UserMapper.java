package com.myproject.library.mapper;

import com.myproject.library.dto.request.user.UserCreationRequest;
import com.myproject.library.dto.request.user.UserUpdateRequest;
import com.myproject.library.dto.response.user.UserResponse;
import com.myproject.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring") // Khai bao de co the inject de dang
public interface UserMapper {
    User toUser(UserCreationRequest userCreationRequest);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest userUpdateRequest); // mapping tu userUpdateRequest sang user
}
