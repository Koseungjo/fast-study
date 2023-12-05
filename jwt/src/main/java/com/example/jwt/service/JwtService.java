package com.example.jwt.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class JwtService {

    private static String secretKey = "java11SpringBootJwtTokenIssueMethod";

    /**
     * create jwt token
     * @param claims
     * @param expireAt
     * @return
     */
    public String create(Map<String,Object> claims, LocalDateTime expireAt){
        // 암호화 방식
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        // 만료 일자
        var _expireAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setExpiration(_expireAt)
                .compact();
    }

    /**
     * validation jwt token
     * @param token
     */

    public void validation(String token){
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        try {
            var result = parser.parseClaimsJws(token);
            result.getBody().entrySet().forEach(value -> {
                log.info("key : {}, value : {}", value.getKey(), value.getValue());
            });
        }catch (Exception e){
            //인증 에러
            if (e instanceof SignatureException){
                throw new RuntimeException("JWT Token Not Valid Exception");
            }
            //시간 만료 에러
            else if (e instanceof ExpiredJwtException) {
                throw new RuntimeException("JWT Token Expired Exception");
            }
            // 다른 모든 에러
            else {
                throw new RuntimeException("JWT Token Validation Exception");
            }
        }
    }

}
