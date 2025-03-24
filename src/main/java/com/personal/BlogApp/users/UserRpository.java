package com.personal.BlogApp.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRpository extends JpaRepository<UserEntity,Long> {
}
