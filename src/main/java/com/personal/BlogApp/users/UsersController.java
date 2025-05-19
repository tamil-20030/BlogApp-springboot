package com.personal.BlogApp.users;

import com.personal.BlogApp.common.dtos.ErrorResponse;
import com.personal.BlogApp.security.JWTService;
import com.personal.BlogApp.users.dtos.CreateUserRequest;
import com.personal.BlogApp.users.dtos.UserResponse;
import com.personal.BlogApp.users.dtos.LoginUserRequest;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;

    public UsersController(UsersService usersService, ModelMapper modelMapper,JWTService jwtService) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
        this.jwtService =jwtService;
    }

    @GetMapping("")
   public  String helo()
    {
        return "hello";
    }
    @RequestMapping("/all")
    public List<UserEntity> allUsers()
    {
        return usersService.getAllUsers();
    }
    @PostMapping("")
    ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest request)
    {
        UserEntity savedUser=usersService.createUser(request);
        URI savedUserUri=URI.create("/users/"+savedUser.getId());
        var userResponse=modelMapper.map(savedUser, UserResponse.class);
        userResponse.setToken(jwtService.createJWT(savedUser.getId()));

        return ResponseEntity.created(savedUserUri).body(userResponse);
    }

    @PostMapping("/login")
    ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request)
    {
    UserEntity savedUser=usersService.loginUser(request.getUserName(), request.getPassword());
        var userResponse=modelMapper.map(savedUser, UserResponse.class);
        userResponse.setToken(jwtService.createJWT(savedUser.getId()));

    return ResponseEntity.ok(userResponse);
    }

    @ExceptionHandler({
            UsersService.UserNotFoundException.class,
            UsersService.InvalidCredentialsException.class
    })
    ResponseEntity<ErrorResponse>  handleException(Exception e)
    {
        String message;
        HttpStatus status;
        if(e instanceof UsersService.UserNotFoundException)
        {
            message = e.getMessage();
            status = HttpStatus.NOT_FOUND;
        }
        else if(e instanceof UsersService.InvalidCredentialsException)
        {
            message = e.getMessage();
            status = HttpStatus.UNAUTHORIZED;
        }
        else {
            message="unknown error";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse response= ErrorResponse.builder()
                .message(message)
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
