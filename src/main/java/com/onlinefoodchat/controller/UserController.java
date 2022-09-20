package com.onlinefoodchat.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.AddCart;
import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.entity.MenuEntity;
import com.onlinefoodchat.entity.MyOrders;
import com.onlinefoodchat.entity.OrderProductList;
import com.onlinefoodchat.entity.UserLogin;
import com.onlinefoodchat.service.EmailSenderService;
import com.onlinefoodchat.service.UserService;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

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
		
		//if (obj != null && randomWithNextInt == Integer.parseInt(userLogin.getOtp())) {
			  if (obj != null) {
			
			session.setAttribute("email", userLogin.getUserEmail());
			session.setAttribute("userId", obj.getUserId());
			return ResponseEntity.ok(userLogin);
		}
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email and password does not match");
		
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
	
	
	/* Add Cart */
	@PostMapping("/addCart")
	public ResponseEntity<String> addCart(@RequestBody AddCart addCart,HttpSession session){
		
 		 service.saveCart(addCart,""+session.getAttribute("email"));
			return ResponseEntity.ok("ok");
		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not ok");
	}

	/* My Order */
	@GetMapping("/myOrder")
	public ModelAndView order(HttpSession session) {
			List<AddCart> myOrder = service.myOrder(""+session.getAttribute("email"));
		modelAndView.setViewName("MyCart");
		modelAndView.addObject("allAddedCart", myOrder);
		return modelAndView;
	}

	/* Order Updated Price */
	@ResponseBody
	@GetMapping("/updatePrice")
	public String updatePrice(@RequestParam String quantity,@RequestParam String totalPrice,@RequestParam String dishId) {
		System.out.println(quantity);
		System.out.println(totalPrice);
		System.out.println(dishId);
		double updatePrice = service.getUpdatePrice(dishId,quantity,totalPrice);
		
		return ""+updatePrice;
	}
	
	
	/* RazorPay Amount Varification */
	@ResponseBody
	@RequestMapping(value="/payment")
	public String getPayment(@RequestBody Map<String, Object> data) throws RazorpayException {
		
		System.out.println("aaya tha");
		System.out.println(data.get("amount"));
		//getting amount from frontend
		double amount = Double.parseDouble(data.get("amount").toString());
		
		//creating razorpayclient object for creating order
		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_sgXOFdvGGQTLKV", "vjBjHIOGkSAlRNiVAlvKz7pE");
		
		//creating jsonObject with all deatils like amount currency
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("amount", amount * 100);
		jsonObject.put("currency", "INR");
		jsonObject.put("receipt", "tx_123456");
		
		//creating order
		com.razorpay.Order order = razorpayClient.orders.create(jsonObject);
		System.out.println("order==="+ order.toString());
		return order.toString();
	}
	
	/* Pay Order */
	
	@ResponseBody
	@PostMapping("/payOrder")
	public ModelAndView payOrder(@ModelAttribute MyOrders myOrders ,HttpSession session) {
		System.out.println("come");
		if(service.PayOrder(myOrders,""+session.getAttribute("email"),""+session.getAttribute("userId")))
		modelAndView.addObject("successMsg", "Order Success full");
		else
			modelAndView.addObject("errorMsg", "Order Not Success full");
		//modelAndView.setViewName("MyCart");
		return modelAndView;
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
		emailSenderService.mailSender(email, subject, mailMessage);
		return "ok";
	}
}
