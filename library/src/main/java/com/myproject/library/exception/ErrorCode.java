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
    USERNAME_INVALID(1003, "user must be at least 8 character"),
    PASSWORD_INVALID(1004, "password must be at least 8 character"),
    ;

    private int code;
    private String message;
}
