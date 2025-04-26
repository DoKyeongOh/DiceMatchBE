package com.odk.pjt.dicematchbe.auth;

import com.odk.pjt.dicematchbe.exception.session.DuplicatedLoginSessionException;
import com.odk.pjt.dicematchbe.exception.session.LoginSessionNotExistException;
import com.odk.pjt.dicematchbe.util.HashEncryptionUtil;
import com.odk.pjt.dicematchbe.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionManagementService {

    private final static String LOGIN_COOKIE_NAME = "jwtToken";
    private final static String JWT_LOGIN_SUBJECT = "login";
    private final Map<String, String> userIdTokenMap = new ConcurrentHashMap<>();

    @Value("${app.login.session-expire-seconds}")
    private int expireSeconds;

    @Value("${app.login.session-hash-key}")
    private String keyString;

    private SecretKey secretKey;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        String key = HashEncryptionUtil.encrypt("SHA-256", keyString);
        secretKey = Keys.hmacShaKeyFor(key.getBytes());
    }

    public Cookie login(String userId) throws Exception {
        String jwsToken = userIdTokenMap.get(userId);

        if (jwsToken != null) {
            Jws<Claims> claimsJws = JwtUtil.parseJws(jwsToken, secretKey);
            Date expiration = claimsJws.getPayload().getExpiration();

            if (expiration.getTime() > new Date().getTime()) {
                throw new DuplicatedLoginSessionException(userId);
            }
        }

        String jws = JwtUtil.createJws(JWT_LOGIN_SUBJECT, userId, expireSeconds, secretKey);
        userIdTokenMap.put(userId, jws);

        Cookie loginCookie = new Cookie(LOGIN_COOKIE_NAME, jws);
        loginCookie.setHttpOnly(true);
        loginCookie.setSecure(false);
        loginCookie.setPath("/");
        loginCookie.setMaxAge(expireSeconds);

        return loginCookie;
    }

    public Cookie logout(String jwtToken) throws Exception {
        Jws<Claims> claimsJws = JwtUtil.parseJws(jwtToken, secretKey);
        Set<String> audience = claimsJws.getPayload().getAudience();

        if (audience == null || audience.isEmpty()) {
            throw new LoginSessionNotExistException();
        }

        String userId = new ArrayList<>(audience).get(0);
        userIdTokenMap.remove(userId);

        Cookie loginCookie = new Cookie("jwtToken", jwtToken);
        loginCookie.setHttpOnly(true);
        loginCookie.setSecure(false);
        loginCookie.setPath("/");
        loginCookie.setMaxAge(0);

        return loginCookie;
    }

}
