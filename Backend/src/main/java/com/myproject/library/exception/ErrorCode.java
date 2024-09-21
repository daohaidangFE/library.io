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
    ;

    private int code;
    private String message;

}
