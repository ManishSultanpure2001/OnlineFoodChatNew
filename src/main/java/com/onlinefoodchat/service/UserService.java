package com.onlinefoodchat.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.entity.MenuEntity;
import com.onlinefoodchat.entity.ReCaptchaResponse;
import com.onlinefoodchat.entity.UserLogin;
import com.onlinefoodchat.repository.ClientRepository;
import com.onlinefoodchat.repository.MenuRepository;
import com.onlinefoodchat.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private  RestTemplate restTemplate;
	@Autowired
	private MenuRepository menuRepo;


	/*
	 * User Registration
	 */
	public boolean createUser(UserLogin user,HttpServletRequest request) {
		String captchaResponse=request.getParameter("g-recaptcha-response");
		String url="https://www.google.com/recaptcha/api/siteverify";
		String params="?secret=6LfAseghAAAAACRGPCLYR_JIa22Ea71YY2dUQqTt&response="+captchaResponse;
		
		ReCaptchaResponse response=restTemplate.exchange(url+params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
//			model.addAttribute("message", "User Registered successfully!");
		System.out.println(response.isSuccess());
		//if(response.isSuccess())
			repo.save(user); 
			return true;
		
	}
	
	/* User Login */
	public UserLogin login(UserLogin userLogin) {
		return repo.findByUserEmailAndUserPassword(userLogin.getUserEmail(), userLogin.getUserPassword());
	}
	/*
	 * User SearchResto
	 */
	
	public List<ClientLogin>getSearchResult(String search) {
		
		Date date=Date.valueOf(LocalDate.now());
		return clientRepository.findByRestoNameContainsAndEndDateGreaterThanEqual(search, date);
		
	}
	
	/* Searched Resto Menu Data */
	public List<MenuEntity>getSearchedMenu(int id,String email) {
		return menuRepo.findByClientemail(email);
		 
	}
}
