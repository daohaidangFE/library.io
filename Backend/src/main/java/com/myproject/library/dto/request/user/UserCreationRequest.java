package com.myproject.library.dto.request.user;



import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    Long id;

    @Size(min = 8, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    String email;
    String address;
    String phone;

//    @DobConstraint(min = 15, message = "INVALID_DOB")
    LocalDate dob;

    String role;
}
