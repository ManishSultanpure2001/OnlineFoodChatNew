package com.onlinefoodchat.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.service.ClientServices;
import com.onlinefoodchat.service.EmailSenderService;

@Controller
public class ClientControler {

	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private ClientServices clintService;

	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/signup")
	public String clintSignup() {
		
		return "login";
	}
	
	@SuppressWarnings("deprecation")
	@GetMapping("/login")
	public String userLogin(@ModelAttribute ClientLogin clintLogin) {
	int incrimentValue=0;
		Date date=new Date();
	if(clintLogin.getClientPlan().equals("One Month"))incrimentValue++;
	else if(clintLogin.getClientPlan().equals("Two Month"))incrimentValue+=2;
	else incrimentValue+=3;
		clintLogin.setStartDate(new java.sql.Date( date.getYear(),date.getMonth(),date.getDate()));
		clintLogin.setEndDate(new java.sql.Date( date.getYear(),date.getMonth()+incrimentValue,date.getDate()));
		clintService.clintRegister(clintLogin);
		return "login";
	}
	
	@ResponseBody
	@GetMapping("/email")
	public String emailSend(@RequestParam String email) {
		System.out.println("amit");
		Random random = new Random();
		int randomWithNextInt = random.nextInt(799999) + 100000;
	String subject="OTP verification ";
	if(emailSenderService.mailSender(email, subject,""+randomWithNextInt)) {
		
	}
		return "ok";
		
	}
}
