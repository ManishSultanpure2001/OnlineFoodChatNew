package com.onlinefoodchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.UserLogin;

public interface UserRepository extends JpaRepository<UserLogin, Integer>{

}
