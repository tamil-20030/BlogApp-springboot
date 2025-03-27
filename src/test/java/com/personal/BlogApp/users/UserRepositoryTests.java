package com.personal.BlogApp.users;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void can_create_users()
    {
    var user=UserEntity.builder()
            .userName("Arun")
            .email("arun@gmail.com")
            .build();

    userRepository.save(user);
    }

    @Test
    @Order(2)
    void can_find_users()
    {
        //add for user
        var user=UserEntity.builder()
                .userName("Arun")
                .email("arun@gmail.com")
                .build();

        userRepository.save(user);

        //test for list
        var users=userRepository.findAll();
        Assertions.assertEquals(1,users.size());
    }
}
