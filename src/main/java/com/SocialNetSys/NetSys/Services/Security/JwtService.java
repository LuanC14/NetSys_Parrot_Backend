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
        String secret = "26462948404D635166546A576E5A7234753778214125442A472D4B614E645267";

        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
