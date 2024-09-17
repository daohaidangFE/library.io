package com.myproject.library.service;

import com.myproject.library.dto.request.AuthenticationRequest;
import com.myproject.library.dto.request.IntrospectRequest;
import com.myproject.library.dto.response.AuthenticationResponse;
import com.myproject.library.dto.response.IntrospectResponse;
import com.myproject.library.entity.User;
import com.myproject.library.exception.AppException;
import com.myproject.library.exception.ErrorCode;
import com.myproject.library.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    // tạo token nếu đăng nhập thành công
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword()); //kiem tra xem dang nhap dung k?
        if(!authenticated) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        var token = generateToken(authenticationRequest.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    //xác minh tính hợp lệ của token
    public IntrospectResponse introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        var token = introspectRequest.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes()); // xác minh chữ kí của token và khoá bí mật

        SignedJWT signedJWT = SignedJWT.parse(token); // phân tích token thành signedJWT

        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime(); // thời gian hiệu lực của token

        var verified =  signedJWT.verify(verifier); //xác minh chữ kí

        return IntrospectResponse.builder()
                .valid(verified && expityTime.after(new Date()))
                .build();
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256); // tao header

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder() // lam body cho payload
                .subject(user.getUsername())   // đại diện cho user đăng nhập
                .issuer("libraryonline.com")  // xác định cái token được issue từ ai
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                )) // xác định thời hạn tồn tại
                .claim("scope", "lấy role, mà chưa làm =))")
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject()); //tao payload o dang JSONObj

        JWSObject jwsObject = new JWSObject(header, payload); //gop chung lai

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes())); // ki 2 loai kia va ca KEY

            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }

    }
}
