package com.onlinefoodchat.controller;

import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
		System.out.println(clientLogin.getClientEmail());
		int incrimentValue = 0;
		Date date = new Date();
		if (clientLogin.getClientPlan().equals("One Month"))
			incrimentValue++;
		else if (clientLogin.getClientPlan().equals("Two Month"))
			incrimentValue += 2;
		else
			incrimentValue += 3;
		clientLogin.setStartDate(new java.sql.Date(date.getYear(), date.getMonth(), date.getDate()));
		clientLogin.setEndDate(new java.sql.Date(date.getYear(), date.getMonth() + incrimentValue, date.getDate()));

		if (clientService.clintRegister(clientLogin) && randomWithNextInt == clientLogin.getOtp()) {
			// con1 = hrService.findByEmailAndPassword(email, password);

			System.out.println("ok");
			return ResponseEntity.ok(clientLogin);
		} else {
			System.out.println("Not ok");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email and password does not match");
		}
		// return modelAndView;
	}

	@PostMapping("/clientLogin")
	public ResponseEntity<Object> clintLogin2(@RequestBody ClientLogin clientLogin, HttpSession session) {
		ClientLogin obj = clientService.cilentLogin(clientLogin);
		System.out.println(clientLogin.getOtp());
		System.out.println(randomWithNextInt);
		System.out.println("come");
//		if (obj != null && randomWithNextInt == clientLogin.getOtp()) {
		if (obj != null) {
			System.out.println("in login");
			System.out.println(clientLogin.getId());
			System.out.println(clientLogin.getClientEmail());
//			HttpSession s=request.getSession(false);
			System.out.println(session);
			session.setAttribute("email", clientLogin.getClientEmail());
			System.out.println("ok");
			return ResponseEntity.ok(clientLogin);
		} else {
			System.out.println("not ok");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email and password does not match");
		}
	}

	@ResponseBody
	@GetMapping("/email")
	public String emailSendWithPlan(@RequestParam String email, @RequestParam String plan) {

		Random random = new Random();

		randomWithNextInt = random.nextInt(799999) + 100000;
		String subject = "OTP verification ";
		String mailMessage = "Your plan is  " + plan + "  Enter OTP for Conformation  " + randomWithNextInt;
		emailSenderService.mailSender(email, subject, mailMessage);

		return "ok";
	}

	@ResponseBody
	@GetMapping("/email1")
	public String emailSend(@RequestParam String email) {

		Random random = new Random();
		randomWithNextInt = random.nextInt(799999) + 100000;
		String subject = "OTP verification ";
		String mailMessage = "  Enter OTP for Conformation  " + randomWithNextInt;
		// emailSenderService.mailSender(email, subject, mailMessage);

		return "ok";
	}
}
