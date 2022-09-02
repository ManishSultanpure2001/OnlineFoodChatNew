package com.onlinefoodchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.repository.ClientRepository;

@Service
public class ClientServices {
		@Autowired
		private ClientRepository clintRepository;
		
		public boolean clintRegister(ClientLogin client) {
			clintRepository.save(client);
			return true;
		}
}
