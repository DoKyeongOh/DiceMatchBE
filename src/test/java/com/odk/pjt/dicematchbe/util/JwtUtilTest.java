package com.odk.pjt.dicematchbe.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Date;

class JwtUtilTest {

    @Test
    public void test1() {
        System.out.println(new Date().getTime());
        System.out.println(new Date(Instant.now().toEpochMilli()).getTime());
        System.out.println(Instant.now().toEpochMilli());
    }

    @Test
    public void test() throws NoSuchAlgorithmException {
        String key = HashEncryptionUtil.encrypt("SHA-256", "mypassword");
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());

        long startTimeMilli = Instant.now().toEpochMilli();
        String jws = JwtUtil.createJws("login", "user1", 3600, secretKey);
        assert jws != null;

        System.out.println(jws);

        Jws<Claims> claims = JwtUtil.parseJws(jws, secretKey);
        Claims payload = claims.getPayload();

        System.out.println(payload.getExpiration().getTime() / 1000);
        System.out.println((startTimeMilli + 3600 * 1000) / 1000);

        assert "login".equals(payload.getSubject());
        assert payload.getAudience().contains("user1");

        // jjwt는 초단위 이하를 버림처리함
        assert payload.getExpiration().getTime() / 1000 >= (startTimeMilli + 3600 * 1000) / 1000;
    }

}