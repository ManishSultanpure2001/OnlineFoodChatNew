package com.onlinefoodchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinefoodchat.entity.ClientLogin;

public interface ClientRepository extends JpaRepository<ClientLogin, Integer>{
		
}
