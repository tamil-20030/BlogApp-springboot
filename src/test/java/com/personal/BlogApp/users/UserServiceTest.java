package com.personal.BlogApp.users;


import com.personal.BlogApp.BlogAppApplication;
import com.personal.BlogApp.users.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest

public class UserServiceTest {
     @Autowired
     UsersService usersService;

     @Test
    void can_create_users()
     {
         var user=usersService.createUser(new CreateUserRequest("john","pass","john@gmail.com"));
         Assertions.assertNotNull(user);
         Assertions.assertEquals("john",user.getUserName());
     }

}
