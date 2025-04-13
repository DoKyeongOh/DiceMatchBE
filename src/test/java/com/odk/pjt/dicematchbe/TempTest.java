package com.odk.pjt.dicematchbe;

import com.odk.pjt.dicematchbe.util.HashEncryptionUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

public class TempTest {

    @Test
    public void test() throws NoSuchAlgorithmException {
        System.out.println(HashEncryptionUtil.encrypt("SHA-256", "mypassword"));
        System.out.println(HashEncryptionUtil.encrypt("SHA-256", "123123"));
        // 5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8
    }

    @Test
    public void test2() throws NoSuchAlgorithmException {
        String key = HashEncryptionUtil.encrypt("SHA-256", "mypassword");
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes());

        String jwt = Jwts.builder()
                .header().keyId("diceMatch").and()
                .issuer("system")
                .subject("login")
                .audience().add("user").and()
                .issuedAt(new Date())
                .notBefore(new Date())
                .expiration(new Date(new Date().getTime() + 1000 * 3600))
                .signWith(secretKey)
                .compact();

        System.out.println(jwt);

        Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt);
        claimsJws.getPayload().values().forEach(System.out::println);
    }
}
