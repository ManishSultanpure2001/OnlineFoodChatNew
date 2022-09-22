package com.onlinefoodchat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyOrders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private int userId;
	private double totalAmount;
	private int totalQuantity;
	private String orderStatus="panding";
	private String restoName;
	private String userEmail;
	
	public MyOrders() {
		super();
		// TODO Auto-generated constructor stub 
	}
	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getRestoName() {
		return restoName;
	}
	public void setRestoName(String restoName) {
		this.restoName = restoName;
	}


	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}


	@Override
	public String toString() {
		return "MyOrders [orderId=" + orderId + ", userId=" + userId + ", totalAmount=" + totalAmount
				+ ", totalQuantity=" + totalQuantity + ", orderStatus=" + orderStatus + ", restoName=" + restoName
				+ ", userEmail=" + userEmail + "]";
	}

	
}
