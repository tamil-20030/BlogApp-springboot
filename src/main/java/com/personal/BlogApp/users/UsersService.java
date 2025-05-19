package com.personal.BlogApp.users;

import com.personal.BlogApp.users.dtos.CreateUserRequest;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

//    @Autowired
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;



    public UsersService(UserRepository userRepository, ModelMapper modelMapper,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }





    public List<UserEntity> getAllUsers()
    {
        return userRepository.findAll();
    }

    public UserEntity createUser(CreateUserRequest u)
    {
        UserEntity newUser = modelMapper.map(u,UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(u.getPassword()));
//
        return userRepository.save(newUser);
    }

    public UserEntity getUser(String UserName)
    {
        return userRepository.findByUserName(UserName).orElseThrow(()->new UserNotFoundException(UserName));
    }
    public UserEntity getUser(Long userId)
    {
        return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
    }

    public UserEntity loginUser(String userName,String password)
    {
        var user=userRepository.findByUserName(userName).orElseThrow(()->new UserNotFoundException(userName));;
        var passMatch=passwordEncoder.matches(password,user.getPassword());
        if(!passMatch) throw new InvalidCredentialsException();
        //TODO:mat user password
        return user;
    }

    public static class UserNotFoundException extends IllegalArgumentException
    {
        public UserNotFoundException(String userName)
        {
            super("User with username " + userName+" not found");
        }
        public UserNotFoundException(Long userId)
        {
            super("User with id" + userId+" not found");
        }
    }

    public static class InvalidCredentialsException extends IllegalArgumentException
    {
        public InvalidCredentialsException() {
            super("Invalid username or password");
        }
    }

}
