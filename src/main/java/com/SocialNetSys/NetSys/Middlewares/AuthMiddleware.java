package com.SocialNetSys.NetSys.Middlewares;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.security.Key;

@Component
public class AuthMiddleware implements HandlerInterceptor {
    @Value("${jwt.secret}")
    protected String decryptedKey;

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {

        final Key secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(decryptedKey));

        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if ( request.getRequestURI().equals("/api/user/signup") || request.getRequestURI().equals("/api/user/password")  ) { return true; }

        if(request.getRequestURI().equals("/api/auth") || request.getRequestURI().equals("/api/publications") ) { return true; }

        if(request.getRequestURI().equals("/api/user/username") || request.getRequestURI().equals("/api/user/email") ) { return  true; }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token not found");
        }

        String token = authHeader.substring(19);

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

        return true;
    }
}
