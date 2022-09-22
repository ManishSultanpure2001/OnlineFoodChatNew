package com.onlinefoodchat.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinefoodchat.entity.AddCart;
import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.entity.MenuEntity;
import com.onlinefoodchat.entity.MyOrders;
import com.onlinefoodchat.entity.Notification;
import com.onlinefoodchat.entity.OrderProductList;
import com.onlinefoodchat.entity.UserLogin;
import com.onlinefoodchat.repository.AddCartRespository;
import com.onlinefoodchat.repository.ClientRepository;
import com.onlinefoodchat.repository.MenuRepository;
import com.onlinefoodchat.repository.MyOrdersRepository;
import com.onlinefoodchat.repository.NotificationRepository;
import com.onlinefoodchat.repository.OrderProductListRepository;
import com.onlinefoodchat.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private MenuRepository menuRepo;
	@Autowired
	private AddCartRespository addCartRespository;
	@Autowired
	private MyOrdersRepository myOrdersRepository;
	@Autowired 
	private NotificationRepository notificationRepository;
	@Autowired
	private OrderProductListRepository orderProductRepo;
	private Matcher matcherPassword;
	private Pattern patternPassword;
	static String email;
	/* User Registration */

	public String createUser(UserLogin user, HttpServletRequest request) {
		

		patternPassword = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Z a-z 0-9 \\s_]).{5,20}$");
		matcherPassword = patternPassword.matcher(user.getUserPassword());
		// if(response.isSuccess())
		if (matcherPassword.find()) {
			repo.save(user);
			return "ok";
		}
		return "password";
	}

	/* User Login */
	public UserLogin login(UserLogin userLogin) {
		email = userLogin.getUserEmail();
		return repo.findByUserEmailAndUserPassword(userLogin.getUserEmail(),userLogin.getUserPassword());
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
	public void saveCart(AddCart addCart, String session) {
		addCart.setUserEmail(session);
		double sum = 0;
		addCart.setTotlePrice(addCart.getMenuPrice() * addCart.getMenuQuantity());
		List<AddCart> findByRestoNameAndUserEmail = addCartRespository
				.findByRestoNameAndUserEmail(addCart.getRestoName(), session);

		if (findByRestoNameAndUserEmail == null || findByRestoNameAndUserEmail.isEmpty()) {

			addCartRespository.deleteDish(addCart.getUserEmail());
			addCart.setSumOfTotlePrice(this.sumOfTotalAmount(findByRestoNameAndUserEmail, addCart));
			addCartRespository.save(addCart);
		} else {
			addCart.setSumOfTotlePrice(this.sumOfTotalAmount(findByRestoNameAndUserEmail, addCart));
			addCartRespository.save(addCart);
		}

	}

	public List<AddCart> myOrder(String usreEmail) {
		List<AddCart> findByUserEmail = addCartRespository.findByUserEmail(usreEmail);
		return findByUserEmail;
	}

	// sum of total amount
	public double sumOfTotalAmount(List<AddCart> findByRestoNameAndUserEmail, AddCart addCart) {
		int i = 0;
		int sum = 0;
		do {
			if (findByRestoNameAndUserEmail.size() == 0) {
				sum = addCart.getTotlePrice();
				return sum;
			}
			sum = sum + findByRestoNameAndUserEmail.get(i).getTotlePrice() + addCart.getTotlePrice();
			i++;
		} while (i < findByRestoNameAndUserEmail.size());
		return sum;
	}

	/* Update Price */
	@Transactional
	public double getUpdatePrice(String dishId, String quantity, String totalPrice) {

		List<AddCart> findByUserEmail = addCartRespository.findByUserEmail(email);
		int i = 0;
		double sum = 0;
		do {
			if (findByUserEmail.size() == 0) {
				return sum;
			}
			sum = sum + findByUserEmail.get(i).getTotlePrice();
			i++;
		} while (i < findByUserEmail.size());
		addCartRespository.updateData(quantity, totalPrice, "" + sum, dishId);
		return sum;
	}

	/* Pay Order */ 
	
	public boolean PayOrder(MyOrders myOrders, String email,String id) {
		int totalQuentity=0;
		double totalPrice=0;
		
		
		
		List<AddCart> findByUserEmail = addCartRespository.findByUserEmail(email);
		myOrders.setRestoName(findByUserEmail.get(0).getRestoName());
		myOrders.setUserEmail(email);
		myOrders.setUserId(Integer.parseInt(id));
//		myOrdersRepository.save(myOrders);
		System.out.println(findByUserEmail.get(0).getMenuName());
		for(int i=0;i<findByUserEmail.size();i++) {
			OrderProductList productList=new OrderProductList();
			totalQuentity=totalQuentity+findByUserEmail.get(i).getMenuQuantity();
			totalPrice=totalPrice+findByUserEmail.get(i).getTotlePrice();
			productList.setMenuName(findByUserEmail.get(i).getMenuName());
			productList.setMyOrders(myOrders);
			productList.setMenuImage(findByUserEmail.get(i).getMenuImage());
			productList.setMenuPrice(findByUserEmail.get(i).getMenuPrice());
			productList.setQuantity(findByUserEmail.get(i).getMenuQuantity());
			orderProductRepo.save(productList);
		}
		myOrders.setTotalAmount(totalPrice);
		myOrders.setTotalQuantity(totalQuentity);
	
		myOrdersRepository.save(myOrders);
		
		Notification notification=new Notification();
		notification.setIdentifyId(Integer.parseInt(id));
		notification.setOrderid(myOrders.getOrderId());
		notification.setUserMessage("Your Order Has been Taken by  "+findByUserEmail.get(0).getRestoName()+" Restorent");
		notification.setRestoName(findByUserEmail.get(0).getRestoName());
		notification.setClintMessage("order Request by "+myOrders.getUserEmail());
		notificationRepository.save(notification);
		return true;
	}

	/* All Notifications */
	public  List<Notification> getAllNotifications(String id) {
		List<Notification> allNotifications =notificationRepository.findByIdentifyId(Integer.parseInt(id));
		return allNotifications;
	}

	/* All Orders List */
	public List<MyOrders> getOrders(String email) {
		List<MyOrders> findByUserEmail = myOrdersRepository.findByUserEmail(email);
	return findByUserEmail;	
	}

	
	/* Cancel Order */
	@Transactional
	public List<MyOrders> getCancelOrder(int id) {
		List<MyOrders> findByRestoName=null;
		try {
			
			MyOrders myOrders=myOrdersRepository.findById(id).get();
			myOrders.setOrderStatus("cancel");
			System.out.println(findByRestoName);
			String orderMessage="order has been Cancled by "+ myOrders.getUserEmail();
			notificationRepository.updateUserNotification("Order Cancel", "Order Has Been Cancel",""+myOrders.getUserId(),""+myOrders.getOrderId());
			notificationRepository.updateClientNotification("Order Cancel",orderMessage,""+myOrders.getUserId(),""+myOrders.getOrderId());
		}
		catch(NullPointerException|ClassCastException  exception) {
		exception.printStackTrace();
		}
		
		return null;
	}

	/* Delete Notification */
	@Transactional
	public void getDeleteNotification(int orderId) {
		notificationRepository.updateUserDeleteNotification(1, orderId);
	}
}
