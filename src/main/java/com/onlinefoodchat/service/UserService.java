package com.onlinefoodchat.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.onlinefoodchat.entity.AddCart;
import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.entity.MenuEntity;
import com.onlinefoodchat.entity.ReCaptchaResponse;
import com.onlinefoodchat.entity.UserLogin;
import com.onlinefoodchat.repository.AddCartRespository;
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
	private RestTemplate restTemplate;
	@Autowired
	private MenuRepository menuRepo;
	@Autowired
	private AddCartRespository addCartRespository;
	private Matcher matcherPassword;
	private Pattern patternPassword;

	/* User Registration */

	public boolean createUser(UserLogin user, HttpServletRequest request) {
		String captchaResponse = request.getParameter("g-recaptcha-response");
		String url = "https://www.google.com/recaptcha/api/siteverify";
		String params = "?secret=6LfAseghAAAAACRGPCLYR_JIa22Ea71YY2dUQqTt&response=" + captchaResponse;

		ReCaptchaResponse response = restTemplate.exchange(url + params, HttpMethod.POST, null, ReCaptchaResponse.class)
				.getBody();
		System.out.println(response.isSuccess());

		patternPassword = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Z a-z 0-9 \\s_]).{5,20}$");
		matcherPassword = patternPassword.matcher(user.getUserPassword());
		// if(response.isSuccess())
		if (matcherPassword.find()) {
			repo.save(user);
			return true;
		}
		return false;
	}

	/* User Login */
	public UserLogin login(UserLogin userLogin) {
		return repo.findByUserEmailAndUserPassword(userLogin.getUserEmail(), userLogin.getUserPassword());
	}

	@Transactional
	public boolean delete(HttpSession session) {
		repo.deleteAccount("" + session.getAttribute("email"));
		return false;
	}

	/* User SearchResto */

	public List<ClientLogin> getSearchResult(String search) {
		Date date = Date.valueOf(LocalDate.now());
		return clientRepository.findByRestoNameContainsAndEndDateGreaterThanEqual(search, date);
	}

	/* Searched Resto Menu Data */
	public List<MenuEntity> getSearchedMenu(int id, String email) {
		return menuRepo.findByClientemail(email);
	}

	/* Reset Password */
	public boolean resetPass(UserLogin userLogin, String email) {

		UserLogin login = repo.findByUserEmail(email);
		patternPassword = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Z a-z 0-9 \\s_]).{5,20}$");
		matcherPassword = patternPassword.matcher(userLogin.getNewPassword());
		if (login.getUserPassword().equals(userLogin.getUserPassword()) && matcherPassword.find()) {
			login.setUserPassword(userLogin.getNewPassword());
			repo.save(login);
			return true;
		}
		return false;
	}

	/* Add Cart */
	@Transactional
	public boolean saveCart(AddCart addCart,String session) {
		addCart.setUserEmail(session);
		
		addCart.setTotlePrice(addCart.getMenuPrice()*addCart.getMenuQuantity());
		List<AddCart> findByRestoNameAndUserEmail = addCartRespository.findByRestoNameAndUserEmail(addCart.getRestoName(), session);
		
		if( findByRestoNameAndUserEmail==null || findByRestoNameAndUserEmail.isEmpty()){
			addCartRespository.deleteDish(addCart.getUserEmail());
			addCartRespository.save(addCart);
		}
		else {
			addCartRespository.save(addCart);
		}
		return true;
	}

	public List<AddCart> myOrder(String usreEmail) {
		List<AddCart> findByUserEmail = addCartRespository.findByUserEmail(usreEmail);
		return findByUserEmail;
	}
}
