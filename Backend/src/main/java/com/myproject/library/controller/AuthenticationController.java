package com.myproject.library.controller;

import com.myproject.library.dto.request.AuthenticationRequest;
import com.myproject.library.dto.response.ApiResponse;
import com.myproject.library.dto.response.AuthenticationResponse;
import com.myproject.library.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        boolean result = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .authenticated(result)
                        .build())
                .build();
    }
}
