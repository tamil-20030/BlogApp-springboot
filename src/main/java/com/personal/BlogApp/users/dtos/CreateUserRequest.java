package com.personal.BlogApp.users.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NonNull

    private String userName;

    @NonNull

    private String password;

    @NonNull

    private  String email;
}
