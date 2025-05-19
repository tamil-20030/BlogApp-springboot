package com.personal.BlogApp.security;

import com.personal.BlogApp.users.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationManager implements AuthenticationManager {

    private JWTService jwtService;
    private UsersService usersService;

    public JWTAuthenticationManager(JWTService jwtService, UsersService usersService) {
        this.jwtService = jwtService;
        this.usersService = usersService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       if(authentication instanceof JWTAuthentication) {
           var jwtAuthentication=(JWTAuthentication)authentication;
           var jwt=jwtAuthentication.getCredentials();
           var userId=jwtService.retrieveUserID(jwt);
           var userEntity=usersService.getUser(userId);

           jwtAuthentication.setAuthenticated(true);
           jwtAuthentication.userEntity=userEntity;
           return jwtAuthentication;
       }
        return null;
    }
}
