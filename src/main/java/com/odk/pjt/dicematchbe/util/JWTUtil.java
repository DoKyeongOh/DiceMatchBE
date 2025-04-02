package com.odk.pjt.dicematchbe.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtil {

    public static String createJWT(String username) {
        return createJWT(username, "secretKey", 60 * 60);
    }

    public static String createJWT(String username, String secretKey) {
        return createJWT(username, secretKey, 60 * 60);
    }

    public static String createJWT(String subject, String secretKey, long tokenDuration) {
        return Jwts.builder()
                .header()
                .keyId("login")
                .and()
                .issuer("system")
                .subject("login")
                .audience().add(subject).and()
                .issuedAt(new Date())
                .notBefore(new Date())
                .expiration(new Date(new Date().getTime() + 1000 * tokenDuration))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

}
