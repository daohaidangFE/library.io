package com.myproject.library.mapper;

import com.myproject.library.dto.request.UserCreateRequest;
import com.myproject.library.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest userCreateRequest);
}
