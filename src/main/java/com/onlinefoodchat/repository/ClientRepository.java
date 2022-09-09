package com.onlinefoodchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlinefoodchat.entity.ClientLogin;

@Repository
public interface ClientRepository extends JpaRepository<ClientLogin, Integer> {
	public ClientLogin findByClientEmailAndClientPassword(@Param("email") String clientEmail,
			@Param("password") String clientPassword);

	public ClientLogin findByClientEmail(@Param("email") String clientEmail);
	
}
