package com.onlinefoodchat.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	

	private ClientLogin clientLogin;

	/* Client Login */
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

	/* Client Login */
	public ClientLogin cilentLogin(ClientLogin client) {
		return clintRepository.findByClientEmailAndClientPassword(client.getClientEmail(), client.getClientPassword());
	}

	/* Client Plan */

	public ClientLogin myPlan(HttpSession session) {

		return clintRepository.findByClientEmail("" + session.getAttribute("email"));

	}

	/* Date Check */
	public boolean dateChack(String date) throws ParseException {
		Date curruntDate = new Date();
		Date newDate = new java.sql.Date(curruntDate.getYear(), curruntDate.getMonth(), curruntDate.getDate());

		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdformat.parse(date);
		Date d2 = sdformat.parse("" + newDate);

		sdformat.format(d1);
		sdformat.format(d2);

		if (d1.compareTo(d2) > 0) {
			// upto date;
			return true;
		}
		return false;
	}

	/* Renew Plan */
	public boolean renewPlan(ClientLogin clientLogin) throws ParseException {

		ClientLogin clientLogin1 = clintRepository.findByClientEmail(clientLogin.getClientEmail());
		Optional<ClientLogin> realData = clintRepository.findById(clientLogin1.getId());
		ClientLogin login = realData.get();
		int incrimentValue = 0;
		Date date = new Date();
		if (clientLogin.getClientPlan().equals("One Month"))
			incrimentValue++;
		else if (clientLogin.getClientPlan().equals("Two Month"))
			incrimentValue += 2;
		else
			incrimentValue += 3;
		login.setStartDate(new java.sql.Date(date.getYear(), date.getMonth(), date.getDate()));
		login.setEndDate(new java.sql.Date(date.getYear(), date.getMonth() + incrimentValue, date.getDate()));
		login.setClientPlan(clientLogin.getClientPlan());
		if (!dateChack("" + login.getEndDate())) {

			clintRepository.saveAndFlush(login);
			return true;
		}
		return false;

	}
}
