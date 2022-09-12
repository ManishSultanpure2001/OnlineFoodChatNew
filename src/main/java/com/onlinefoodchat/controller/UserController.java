package com.onlinefoodchat.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.entity.MenuEntity;
import com.onlinefoodchat.entity.ReCaptchaResponse;
import com.onlinefoodchat.entity.UserLogin;
import com.onlinefoodchat.service.EmailSenderService;
import com.onlinefoodchat.service.UserService;
import com.onlinefoodchat.util.CaptchaUtil;

import cn.apiclub.captcha.Captcha;

@Controller
public class UserController {
	private static final String String = null;
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
		System.out.println("user= "+user);
		if(service.createUser(user,request)) {
			System.out.println("come");
			return ResponseEntity.ok(user);
		}
		else {
			System.out.println("Not Come");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not registerd");
		}
		
	}
	
	/* User Login */
	@PostMapping("/userLogin")
	public ResponseEntity<Object> clintLogin2(@RequestBody UserLogin userLogin, HttpSession session) {
		UserLogin obj = service.login(userLogin);
		System.out.println(userLogin.getOtp());
		System.out.println(randomWithNextInt);
		System.out.println("come");
//		if (obj != null && randomWithNextInt == clientLogin.getOtp()) {
			 if (obj != null) {
			System.out.println("in login");
			System.out.println(userLogin.getUserId());
			System.out.println(userLogin.getUserEmail());
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
	public ModelAndView searchMenu(@RequestParam int searchId,@RequestParam String searchEmail) {	
		List<MenuEntity> searchedMenu = service.getSearchedMenu(searchId,searchEmail);
		System.out.println("data=   "+searchedMenu);
		modelAndView.addObject("allMenu",searchedMenu);
		modelAndView.setViewName("UserSearchedDishList");
	return modelAndView;
	}
	
	/* Email Send Without Plan */
	@ResponseBody
	@GetMapping("/loginEmail")
	public String emailSend(@RequestParam String email) {

		Random random = new Random();
		randomWithNextInt = random.nextInt(799999) + 100000;
		String subject = "OTP verification ";
		String mailMessage = "  Enter OTP for Conformation  " + randomWithNextInt;
		//emailSenderService.mailSender(email, subject, mailMessage);
		return "ok";
	}
}
