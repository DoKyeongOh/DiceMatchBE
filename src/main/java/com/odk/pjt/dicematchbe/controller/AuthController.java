package com.odk.pjt.dicematchbe.controller;

import com.odk.pjt.dicematchbe.util.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Value("${app.token.secret-key}")
    private String secretKey;

    @Value("${app.token.expire-seconds}")
    private long tokenDuration;

    @GetMapping("basic")
    public String loginWithBasic(String id, String password) {
        // account service를 통해 id, password가 존재하는지 체크

        // 존재하면 JWT 발급
        String jwtKey = JWTUtil.createJWT("user id", secretKey, tokenDuration);

        // 쿠키에 넣어주기

        return "user id";
    }

    @GetMapping("google")
    public String loginWithGoogle() {
        return "user id";
    }

    @DeleteMapping
    public String logout() {
        return "user id";
    }



}
