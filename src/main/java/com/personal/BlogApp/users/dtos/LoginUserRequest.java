package com.personal.BlogApp.users.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class LoginUserRequest {

    @NonNull

    String userName;
     @NonNull
    String password;
}
