package com.myproject.library.dto.response;

import com.myproject.library.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;

    String username;
    String email;
    String address;
    String phone;
    LocalDate dob;
    Role role;
}
