package com.SocialNetSys.NetSys.Services.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.UUID;
@Service
public class JwtService implements IJwtService {
    @Value("${jwt.secret}")
    private String decryptedKey;

    public String generateToken(UUID userID) {

        long EXPIRATION_TIME = 7200000;

        String userId = userID.toString();

        return Jwts
                .builder()
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(genSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key genSignInKey() {

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(decryptedKey));
    }
}
