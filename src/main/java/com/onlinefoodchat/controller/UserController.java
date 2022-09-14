package com.onlinefoodchat.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.entity.MenuEntity;
import com.onlinefoodchat.entity.UserLogin;
import com.onlinefoodchat.service.EmailSenderService;
import com.onlinefoodchat.service.UserService;

import cn.apiclub.captcha.Captcha;

@Controller
public class UserController {
	ModelAndView modelAndView=new ModelAndView();
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private UserService service;
	int randomWithNextInt;
	@RequestMapping("/register")
		public ModelAndView registerUser() {
		
		modelAndView.setViewName("userSignup");
		return modelAndView;
	}
	@RequestMapping("/userLogin")
	public ModelAndView loginUser() {
		
		modelAndView.setViewName("userLogin");
		return modelAndView;
	}

	/* User SignUp */
	@PostMapping("/save")
	public ResponseEntity<Object> saveUser(@RequestBody UserLogin user, HttpServletRequest request) {
		if(user.getCaptcha().equals(request.getSession().getAttribute("captcha"))) {
		if(service.createUser(user,request)) {
			return ResponseEntity.ok(user);
		}
		}
		 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not registerd");
		 
		
	}
	
	/* User Login */
	@PostMapping("/userLogin")
	public ResponseEntity<Object> clintLogin2(@RequestBody UserLogin userLogin, HttpSession session) {
		UserLogin obj = service.login(userLogin);
		
//		if (obj != null && randomWithNextInt == clientLogin.getOtp()) {
			 if (obj != null) {
			
			session.setAttribute("email", userLogin.getUserEmail());
			return ResponseEntity.ok(userLogin);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email and password does not match");
		}
	}
 
	/* Search Result */
	@ResponseBody
	@GetMapping("/search")
	public List<ClientLogin> search(@RequestParam String search){
		
		return service.getSearchResult(search);
	}
	
	/* All Searched Menu */
	@ResponseBody
	@GetMapping("/searchMenu")
	public ModelAndView searchMenu(@RequestParam int searchId,@RequestParam String searchEmail,@RequestParam String restoName) {	
		List<MenuEntity> searchedMenu = service.getSearchedMenu(searchId,searchEmail);
		
		modelAndView.addObject("allMenu",searchedMenu);
		modelAndView.addObject("restoName",restoName);
		modelAndView.setViewName("UserSearchedDishList");
	return modelAndView;
	}

	/* Delete User Account */
	@GetMapping("/deleteUser")
	public ModelAndView deleteUser(HttpSession session) {
			service.delete(session);
		modelAndView.setViewName("userLogin");
		return modelAndView;
	}
	
	/* Password Reset */
	
	@PostMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestBody UserLogin userLogin,HttpSession session){
		
 		 if(service.resetPass(userLogin,""+session.getAttribute("email"))&&userLogin.getOtp().equals(""+randomWithNextInt)) {
			// if(service.resetPass(userLogin,""+session.getAttribute("email"))) {
			System.out.println("true Condition"+userLogin.getOtp());
			return ResponseEntity.ok("ok");}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not ok");
	}
	
	/* Email Send Without Plan */
	@ResponseBody
	@GetMapping("/loginEmail")
	public String emailSend(@RequestParam String email) {

		Random random = new Random();
		randomWithNextInt = random.nextInt(799999) + 100000;
		System.out.println(randomWithNextInt);
		String subject = "OTP verification ";
		String mailMessage = "  Enter OTP for Conformation  " + randomWithNextInt;
		//emailSenderService.mailSender(email, subject, mailMessage);
		return "ok";
	}
}
