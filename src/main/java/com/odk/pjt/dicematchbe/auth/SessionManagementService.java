package com.odk.pjt.dicematchbe.auth;

import com.odk.pjt.dicematchbe.exception.DuplicatedLoginSessionException;
import com.odk.pjt.dicematchbe.util.HashEncryptionUtil;
import com.odk.pjt.dicematchbe.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionManagementService {

    private final Map<String, String> userIdTokenMap = new ConcurrentHashMap<>();

    @Value("${app.token.expire-seconds}")
    private int expireSeconds;

    @Value("${app.token.hash-key}")
    private String keyString;

    private SecretKey secretKey;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        String key = HashEncryptionUtil.encrypt("SHA-256", keyString);
        secretKey = Keys.hmacShaKeyFor(key.getBytes());
    }

    public void loginUser(String userId) throws DuplicatedLoginSessionException {
        String jwsToken = userIdTokenMap.get(userId);

        if (jwsToken == null) {
            String jws = JwtUtil.createJws("login", userId, expireSeconds, secretKey);
            userIdTokenMap.put(userId, jws);
            return;
        }

        Jws<Claims> claimsJws = JwtUtil.parseJws(jwsToken, secretKey);
        Date expiration = claimsJws.getPayload().getExpiration();

        if (expiration.before(new Date())) {
            String jws = JwtUtil.createJws("login", userId, expireSeconds, secretKey);
            userIdTokenMap.put(userId, jws);
            return;
        }

        throw new DuplicatedLoginSessionException(userId);
    }

    public synchronized void logoutUser(String userId) {
        userIdTokenMap.remove(userId);
    }

}
