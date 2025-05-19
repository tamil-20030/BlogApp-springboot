package com.personal.BlogApp.users.dtos;

import lombok.Data;

@Data
public class UserResponse {


    private Long id;
    private String userName;
    private String email;
    private String bio;
    private String image;
    private String token;
}
