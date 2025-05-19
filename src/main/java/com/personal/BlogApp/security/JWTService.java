package com.personal.BlogApp.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private static final String JWT_KEY="hakoonamatatahakoonamatata";
    private Algorithm algorithm=Algorithm.HMAC256(JWT_KEY);

    public String createJWT(Long userId)
    {
        return JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date())
                //TODO: set expiry parameters
                .sign(algorithm);
    }

    public Long retrieveUserID(String JWTString)
    {

        var decoded=JWT.decode(JWTString);
        Long userId=Long.valueOf(decoded.getSubject());
        return userId;
    }


}
