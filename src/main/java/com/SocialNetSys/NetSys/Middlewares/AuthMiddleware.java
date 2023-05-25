package com.SocialNetSys.NetSys.Middlewares;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.security.Key;

@Component
public class AuthMiddleware implements HandlerInterceptor {
    private String decryptedKey = "26462948404D635166546A576E5A7234753778214125442A472D4B614E645267";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final Key secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(decryptedKey));
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token;

            if (authHeader.contains("=>")) {
                token = authHeader.substring(19);
            } else {
                token = authHeader.substring(7);
            }

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String userId = claims.getSubject();
                request.setAttribute("user_id", userId);
            } catch (Exception e) {
                throw new RuntimeException("Invalid Token", e);
            }
        }
        return true;
    }
}
