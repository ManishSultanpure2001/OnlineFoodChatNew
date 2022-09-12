package com.onlinefoodchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.onlinefoodchat.entity.UserLogin;

public interface UserRepository extends JpaRepository<UserLogin, Integer>{
public UserLogin findByUserEmailAndUserPassword(@Param("email") String userEmail,
		@Param("password") String userPassword);
}
