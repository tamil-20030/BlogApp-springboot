package com.personal.BlogApp.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class JWTServiceTests {

    JWTService jwtService = new JWTService();
    @Test
    public void canCreateJWT()
    {
        var JWT=jwtService.createJWT(101L);
        Assertions.assertNotNull(JWT);

    }
}
