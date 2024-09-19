package com.myproject.library.dto.response;


import com.myproject.library.entity.Permission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRespone {
    String name;
    String description;
    Set<PermissionResponse> permissions;
}
