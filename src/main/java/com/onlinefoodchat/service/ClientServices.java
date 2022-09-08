package com.onlinefoodchat.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.repository.ClientRepository;

@Service
public class ClientServices {
	boolean validate = true;
	@Autowired
	private ClientRepository clintRepository;

	public boolean clintRegister(ClientLogin client) {

		Pattern patternPassword = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Z a-z 0-9 \\s_]).{5,20}$");
		Matcher matcherPassword = patternPassword.matcher(client.getClientPassword());

		if (!matcherPassword.find())
			validate = false;

		Pattern patternPhone = Pattern.compile("^[0-9]{10}$");
		Matcher matcherPhone = patternPhone.matcher(client.getClientPhone());
		if (!matcherPhone.find())
			validate = false;

		if (validate)
			clintRepository.save(client);

		return validate;
	}

	public ClientLogin cilentLogin(ClientLogin client) {
		return clintRepository.findByClientEmailAndClientPassword(client.getClientEmail(), client.getClientPassword());
	}
}
