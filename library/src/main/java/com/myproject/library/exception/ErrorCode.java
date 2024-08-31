package com.myproject.library.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1001, "user existed"),
    USER_INVALID(1002, "user must be at least 8 character")
    ;

    private int code;
    private String message;
}
