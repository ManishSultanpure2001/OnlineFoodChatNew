 package com.onlinefoodchat.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.entity.MyOrders;
import com.onlinefoodchat.entity.OrderProductList;
import com.onlinefoodchat.service.ClientServices;
import com.onlinefoodchat.service.EmailSenderService;

@Controller
public class ClientControler extends HttpServlet {
	private int randomWithNextInt;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private ClientServices clientService;
	List<MyOrders>list;
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

	/* Client Registration */
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
	//	if(randomWithNextInt == clientLogin.getOtp()) {
		if (clientService.clintRegister(clientLogin)) {
			return ResponseEntity.ok(clientLogin);
		//}
		}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email and password does not match");
		 
	}

	/* Client Login */
	@PostMapping("/clientLogin")
	public ResponseEntity<Object> clintLogin2(@RequestBody ClientLogin clientLogin, HttpSession session) {
		ClientLogin obj = clientService.cilentLogin(clientLogin);
		//if (obj != null && (randomWithNextInt == clientLogin.getOtp()&&randomWithNextInt!= 0)) {
			 if (obj != null) {
			System.out.println(randomWithNextInt);
			session.setAttribute("email", clientLogin.getClientEmail());
			session.setAttribute("restoName", obj.getRestoName());
			session.setAttribute("clientId", obj.getId());
			return ResponseEntity.ok(clientLogin);
		//}
		
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
	 
		/* View Order */
		@GetMapping("/viewOrder")
		public ModelAndView viewOrder(HttpSession session) {
			List<MyOrders> allOrder = clientService.getAllOrder(""+session.getAttribute("email"));
			modelAndView.setViewName("ListOfClientOrder");
			modelAndView.addObject("data",allOrder);
			return modelAndView;
		}
		
		/* List Of Orders */
		@GetMapping("/menuList")
		public ModelAndView listOfMenu(@ModelAttribute("menuId") int id,HttpSession session) {
			List<OrderProductList> menuList = clientService.getMenuList(id);
			modelAndView.setViewName("MenuListOfClientOrder");
			session.setAttribute("orderId",id);
			modelAndView.addObject("data",menuList);
			return modelAndView;
		}
		
		/* Delete Order */
		@GetMapping("/deleteOrder")
		public ModelAndView deleteOrder(@ModelAttribute("menuId") int id,HttpSession session) {
			List<MyOrders> deletedOrder = clientService.getDeletedOrder(id,""+session.getAttribute("restoName"));
			modelAndView.setViewName("ListOfClientOrder");
			modelAndView.addObject("data",deletedOrder);
			return modelAndView;
		}
		
		/* Order Conformation */
		@GetMapping("/orderPickup")
	public ModelAndView orderPickup(HttpSession session) {
			
			System.out.println(""+session.getAttribute("orderId"));
			List<MyOrders> list=clientService.updateOrderStatus(""+session.getAttribute("orderId"),""+session.getAttribute("restoName"));
		
			
//			modelAndView.addObject("successMsg","Order Deleted SuccessFully");else
//			modelAndView.addObject("errorMsg","Order Not Deleted SuccessFully");
			modelAndView.setViewName("ListOfClientOrder");
			modelAndView.addObject("data",list);
			return modelAndView;
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
		emailSenderService.mailSender(email, subject, mailMessage);
		return "ok";
	}
	
}
