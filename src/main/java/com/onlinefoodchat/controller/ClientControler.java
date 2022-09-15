 package com.onlinefoodchat.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.service.ClientServices;
import com.onlinefoodchat.service.EmailSenderService;

@Controller
public class ClientControler extends HttpServlet {
	private int randomWithNextInt;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private ClientServices clientService;
	
	ModelAndView modelAndView=new ModelAndView();
	Date date;
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}

	@GetMapping("/signup")
	public String clientSignup1() {

		return "clientSignUp";
	}

	@GetMapping("/login")
	public String clientLogin1() {

		return "clientLogin";
	}

	@SuppressWarnings("deprecation")
	@PostMapping("/clientSignup")
	public ResponseEntity<Object> clientSignUp2(@RequestBody ClientLogin clientLogin) {
		int incrimentValue = 0;
		date = new Date();
		if (clientLogin.getClientPlan().equals("One Month"))
			incrimentValue++;
		else if (clientLogin.getClientPlan().equals("Two Month"))
			incrimentValue += 2;
		else
			incrimentValue += 3;
		clientLogin.setStartDate(new java.sql.Date(date.getYear(), date.getMonth(), date.getDate()));
		clientLogin.setEndDate(new java.sql.Date(date.getYear(), date.getMonth() + incrimentValue, date.getDate()));

		if (clientService.clintRegister(clientLogin) && randomWithNextInt == clientLogin.getOtp()) {
			return ResponseEntity.ok(clientLogin);
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email and password does not match");
		 
	}

	@PostMapping("/clientLogin")
	public ResponseEntity<Object> clintLogin2(@RequestBody ClientLogin clientLogin, HttpSession session) {
		ClientLogin obj = clientService.cilentLogin(clientLogin);
//		if (obj != null && randomWithNextInt == clientLogin.getOtp()) {
			 if (obj != null) {
			session.setAttribute("email", clientLogin.getClientEmail());
			return ResponseEntity.ok(clientLogin);
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email and password does not match");
		
	}
	/* Client Plan*/
	 
	@RequestMapping("/myPlan")
	public ModelAndView myPlan1(HttpSession session) {
		ClientLogin planEntity = clientService.myPlan(session);
		modelAndView.setViewName("ClientPlan");
		modelAndView.addObject("data",planEntity);
		return modelAndView;
	}
	
	/* Plan Checker */
	@ResponseBody
	@GetMapping("/plan")
	public String checkPlan(@RequestParam String lastDate) throws ParseException {
		boolean dateChack = clientService.dateChack(lastDate);
		if(dateChack)
			return "ok";
	 return "not ok";
	}
	
	/* Renew Plan */

 
		@SuppressWarnings("deprecation")
		@PostMapping("/clientPlan")
		public ResponseEntity<Object> renewPlan(@RequestBody ClientLogin clientLogin) throws ParseException {
			//if (clientService.renewPlan(clientLogin) && randomWithNextInt == clientLogin.getOtp()) {
			if (clientService.renewPlan(clientLogin)) {
				System.out.println("pass");
				return ResponseEntity.ok(clientLogin);
			}
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email and password does not match");
			

		}
	 
	
	/* Email Send With Plan */
	@ResponseBody
	@GetMapping("/email")
	public String emailSendWithPlan(@RequestParam String email, @RequestParam String plan) {

		Random random = new Random();

		randomWithNextInt = random.nextInt(799999) + 100000;
		String subject = "OTP verification ";
		String mailMessage = "Your plan is  " + plan + "  Enter OTP for Conformation  " + randomWithNextInt;
		//emailSenderService.mailSender(email, subject, mailMessage);
		return "ok";
	}

	/* Email Send Without Plan */
	@ResponseBody
	@GetMapping("/email1")
	public String emailSend(@RequestParam String email) {

		Random random = new Random();
		randomWithNextInt = random.nextInt(799999) + 100000;
		String subject = "OTP verification ";
		String mailMessage = "  Enter OTP for Conformation  " + randomWithNextInt;
		//emailSenderService.mailSender(email, subject, mailMessage);
		return "ok";
	}
	
}
