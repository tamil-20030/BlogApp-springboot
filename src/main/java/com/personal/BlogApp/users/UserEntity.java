package com.personal.BlogApp.users;

import jakarta.persistence.*;

import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


@Entity(name = "users")

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable=false)
    private Long id;

    @Column(name="name",nullable=false)
    @NonNull
    private String userName;

    @Column(name="password",nullable=false)
    @NonNull
    private String password;

    @Column(nullable=false)
    @NonNull
    private String email;

    @Column(nullable=true)
    @Nullable
    private String bio;

    @Column(nullable=true)
    @Nullable
    private String image;


}
