package com.myproject.library.mapper;


import com.myproject.library.dto.request.RoleRequest;
import com.myproject.library.dto.response.RoleRespone;
import com.myproject.library.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest roleRequest);
    RoleRespone toRoleRespone(Role role);
}
