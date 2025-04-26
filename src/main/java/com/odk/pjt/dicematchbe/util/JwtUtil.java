package com.odk.pjt.dicematchbe.util;

import com.odk.pjt.dicematchbe.exception.DiceMatchException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

public class JwtUtil {

    public static String createJws(String subject, String audience, int expireSeconds, Key key) {
        long now = Instant.now().toEpochMilli();
        return Jwts.builder()
                .header().keyId("diceMatch").and()
                .issuer("system")
                .subject(subject)
                .audience().add(audience).and()
                .issuedAt(new Date(now))
                .notBefore(new Date(now))
                .expiration(new Date(now + 1000L * expireSeconds))
                .signWith(key)
                .compact();
    }

    public static Jws<Claims> parseJws(String jwt, SecretKey key) throws Exception {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt);
        } catch (Exception e) {
            throw new Exception("잘못된 토큰 값입니다. 관리자에게 문의하세요.");
        }
    }

}
