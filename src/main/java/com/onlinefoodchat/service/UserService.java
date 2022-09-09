package com.onlinefoodchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodchat.entity.UserLogin;
import com.onlinefoodchat.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	public void createUser(UserLogin user) {
		repo.save(user);
	}
}
