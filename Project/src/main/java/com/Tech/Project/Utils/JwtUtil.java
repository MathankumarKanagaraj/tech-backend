package com.Tech.Project.Utils;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, "pm8u512i8n/fAWNZCIT/44uRNQYgozUM+8ajx12oC3g=")
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey("pm8u512i8n/fAWNZCIT/44uRNQYgozUM+8ajx12oC3g=")
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey("pm8u512i8n/fAWNZCIT/44uRNQYgozUM+8ajx12oC3g=")
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public String generateRefreshToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .compact();
    }

    Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode("pm8u512i8n/fAWNZCIT/44uRNQYgozUM+8ajx12oC3g=");
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
