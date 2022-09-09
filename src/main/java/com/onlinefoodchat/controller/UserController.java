package com.onlinefoodchat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.UserLogin;
import com.onlinefoodchat.service.UserService;
import com.onlinefoodchat.util.CaptchaUtil;

import cn.apiclub.captcha.Captcha;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping("/register")
	//public String registerUser(Model model) {
		public ModelAndView registerUser() {
		ModelAndView modelAndView=new ModelAndView();
		UserLogin user = new UserLogin();
		getCaptcha(user);
		modelAndView.addObject("hidden",user.getHiddenCaptcha());
		modelAndView.addObject("capchaImage",user.getRealCaptcha());
		modelAndView.setViewName("userSignup");
		return modelAndView;
	}

	@PostMapping("/save")
	public String saveUser(@ModelAttribute UserLogin user, Model model) {
		if (user.getCaptcha().equals(user.getHiddenCaptcha())) {
			service.createUser(user);
			model.addAttribute("message", "User Registered successfully!");
			return "redirect:allUsers";
		} else {
			model.addAttribute("message", "Invalid Captcha");
			getCaptcha(user);
			model.addAttribute("user", user);
		}
		return "registerUser";
	}
 

	private void getCaptcha(UserLogin user) {
		Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
		user.setHiddenCaptcha(captcha.getAnswer());
		user.setCaptcha(""); // value entered by the User
		user.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));

	}

}
