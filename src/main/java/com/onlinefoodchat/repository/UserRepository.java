package com.onlinefoodchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlinefoodchat.entity.UserLogin;

public interface UserRepository extends JpaRepository<UserLogin, Integer> {
//	public UserLogin getByUserEmailAndUserPassword(@Param("email") String userEmail,
//			@Param("password") String userPassword);
	
	public UserLogin findByUserEmail(@Param("email") String userEmail);
	
	@Modifying
	@Query(value="delete from user_login where user_email=:email",nativeQuery = true)
	public void deleteAccount(@Param("email") String email);

	public UserLogin findByUserEmailAndUserPassword(String userEmail, String userPassword);

	
}
