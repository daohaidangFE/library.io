package com.myproject.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    INVALID_KEY_EXCEPTION(1001, "Invalid Key Exception"),
    USER_EXISTED(1002, "user existed"),
    USER_NOT_EXISTED(1003, "user not existed"),
    USERNAME_INVALID(1004, "user must be at least 8 character"),
    PASSWORD_INVALID(1005, "password must be at least 8 character"),
    UNAUTHORIZED_EXCEPTION(1006, "Unauthorized Exception"),
    INVALID_DOB(1007, "Invalid Date of Birth"),
    PERMISSION_NOT_EXISTED(1008, "Permission not existed"),
    PERMISSION_EXISTED(1009, "Permission already existed"),
    ROLE_NOT_EXISTED(1010, "Role not existed"),
    ROLE_EXISTED(1011, "Role already existed"),
    ;

    private int code;
    private String message;

}
