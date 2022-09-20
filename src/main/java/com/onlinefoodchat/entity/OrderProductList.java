package com.onlinefoodchat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderProductList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String menuName;
	private String menuImage;
	private double menuPrice;
	private int quantity;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderId")
	private MyOrders myOrders;
	
	
	public String getMenuImage() {
		return menuImage;
	}
	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}
	public double getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(double menuPrice) {
		this.menuPrice = menuPrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public MyOrders getMyOrders() {
		return myOrders;
	}
	public void setMyOrders(MyOrders myOrders) {
		this.myOrders = myOrders;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderProductList [id=" + id + ", menuName=" + menuName + ", menuImage=" + menuImage + ", menuPrice="
				+ menuPrice + ", quantity=" + quantity + ", myOrders=" + myOrders + "]";
	}

}
