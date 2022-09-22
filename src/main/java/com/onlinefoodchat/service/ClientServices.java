package com.onlinefoodchat.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.entity.MyOrders;
import com.onlinefoodchat.entity.Notification;
import com.onlinefoodchat.entity.OrderProductList;
import com.onlinefoodchat.repository.ClientRepository;
import com.onlinefoodchat.repository.MyOrdersRepository;
import com.onlinefoodchat.repository.NotificationRepository;
import com.onlinefoodchat.repository.OrderProductListRepository;

@Service
public class ClientServices {
	
	@Autowired
	private ClientRepository clintRepository;
	@Autowired 
	private MyOrdersRepository myOrdersRepository;
	@Autowired
	private OrderProductListRepository listRepository;
	@Autowired
	private NotificationRepository notificationRepository;
	private ClientLogin clientLogin;

	/* Client Login */
	public String clintRegister(ClientLogin client) {

		Pattern patternPassword = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Z a-z 0-9 \\s_]).{5,20}$");
		Matcher matcherPassword = patternPassword.matcher(client.getClientPassword());

		if (!matcherPassword.find())
			return "password";

		Pattern patternPhone = Pattern.compile("^[0-9]{10}$");
		Matcher matcherPhone = patternPhone.matcher(client.getClientPhone());
		if (!matcherPhone.find())
			return "mobileNumber";

			clintRepository.save(client);

		return "ok";
	}

	/* Client Login */
	public ClientLogin cilentLogin(ClientLogin client) {
		return clintRepository.findByClientEmailAndClientPassword(client.getClientEmail(), client.getClientPassword());
	}

	/* Client Plan */

	public ClientLogin myPlan(HttpSession session) {

		return clintRepository.findByClientEmail("" + session.getAttribute("email"));

	}

	/* Date Check */
	public boolean dateChack(String date) throws ParseException {
		Date curruntDate = new Date();
		Date newDate = new java.sql.Date(curruntDate.getYear(), curruntDate.getMonth(), curruntDate.getDate());

		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = sdformat.parse(date);
		Date d2 = sdformat.parse("" + newDate);

		sdformat.format(d1);
		sdformat.format(d2);

		if (d1.compareTo(d2) > 0) {
			// upto date;
			return true;
		}
		return false;
	}

	/* Renew Plan */
	public boolean renewPlan(ClientLogin clientLogin) throws ParseException {

		ClientLogin clientLogin1 = clintRepository.findByClientEmail(clientLogin.getClientEmail());
		Optional<ClientLogin> realData = clintRepository.findById(clientLogin1.getId());
		ClientLogin login = realData.get();
		int incrimentValue = 0;
		Date date = new Date();
		if (clientLogin.getClientPlan().equals("One Month"))
			incrimentValue++;
		else if (clientLogin.getClientPlan().equals("Two Month"))
			incrimentValue += 2;
		else
			incrimentValue += 3;
		login.setStartDate(new java.sql.Date(date.getYear(), date.getMonth(), date.getDate()));
		login.setEndDate(new java.sql.Date(date.getYear(), date.getMonth() + incrimentValue, date.getDate()));
		login.setClientPlan(clientLogin.getClientPlan());
		if (!dateChack("" + login.getEndDate())) {

			clintRepository.saveAndFlush(login);
			return true;
		}
		return false;

	}

	/* List Of Resto Orders */
	
	public List<MyOrders> getAllOrder(String email) {
		ClientLogin clientEmail = clintRepository.findByClientEmail(email);
		try{
		return myOrdersRepository.findByRestoName(clientEmail.getRestoName());
		}
		catch(NullPointerException exception) {
			System.out.println("exception sads");
			return myOrdersRepository.findByRestoName(clientEmail.getRestoName());
		}
	}

	/* List Of Menu in Order */
	@Transactional
	public List<OrderProductList> getMenuList(int id) {
		try{return listRepository.menuList(id);}
		catch(NullPointerException exception) {
			System.out.println("exception sads");
			return listRepository.menuList(id);
		}
		
	}

	/* Delete Order by Resto Side */

	@Transactional
	public List<MyOrders> getDeletedOrder(int id,String restoName) {
		List<MyOrders> findByRestoName=null;
		try {
			System.out.println(restoName);
			MyOrders myOrders=myOrdersRepository.findById(id).get();
			int flag=listRepository.deleteByMyOrders(myOrders);
			findByRestoName = myOrdersRepository.findByRestoName(restoName);
			System.out.println(findByRestoName);
			String orderMessage="order has been Cancled by "+ restoName+" Restorent";
			notificationRepository.updateUserNotification("Order Deleted", orderMessage,""+myOrders.getUserId(),""+myOrders.getOrderId());
		}
		catch(NullPointerException|ClassCastException  exception) {
		exception.printStackTrace();
		}
		return findByRestoName;
	}
	
	/* Update Status */
	@Transactional
	public List<MyOrders> updateOrderStatus(String id,String restoName) {
			//listRepository.updateStatus("completed", id);
		String orderMessage="order has been Packed by "+ restoName+" Restorent";
			MyOrders myOrders=myOrdersRepository.findById(Integer.parseInt(id)).get();
			myOrders.setOrderStatus("completed");
			myOrdersRepository.save(myOrders);
			notificationRepository.updateUserNotification("completed", orderMessage,""+myOrders.getUserId(),""+myOrders.getOrderId());
	List<MyOrders> findByRestoName = myOrdersRepository.findByRestoName(restoName);
	return findByRestoName;
	}
	
	/* Get All Notifications */
	public List<Notification> getAllNotifications(String id,String restoName) {
		List<Notification> findByIdentifyIdOrRestoName = notificationRepository.findByIdentifyIdOrRestoName(Integer.parseInt(id), restoName);
		return findByIdentifyIdOrRestoName;
	}

	/* Delete Notifications */
	@Transactional
	public void getDeleteNotification(int orderId) {
		notificationRepository.updateClientDeleteNotification(1, orderId);
		
	}

	public String getRestoName(String email) {
		ClientLogin findByClientEmail = clintRepository.findByClientEmail(email);
		return findByClientEmail.getRestoName();
		
	}
}
