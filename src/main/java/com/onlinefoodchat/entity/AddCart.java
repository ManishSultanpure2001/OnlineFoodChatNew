package com.onlinefoodchat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AddCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	private String userEmail;
	private String menuName;
	private String restoName;
	private String menuImage;
	private int menuPrice;
	private int totlePrice;
	private double sumOfTotlePrice;
	private int menuQuantity;
	
	
	public double getSumOfTotlePrice() {
		return sumOfTotlePrice;
	}
	public void setSumOfTotlePrice(double sumOfTotlePrice) {
		this.sumOfTotlePrice = sumOfTotlePrice;
	}
	public String getMenuImage() {
		return menuImage;
	}
	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}
	public int getMenuQuantity() {
		return menuQuantity;
	}
	public void setMenuQuantity(int menuQuantity) {
		this.menuQuantity = menuQuantity;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getRestoName() {
		return restoName;
	}
	public void setRestoName(String restoName) {
		this.restoName = restoName;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	public int getTotlePrice() {
		return totlePrice;
	}
	public void setTotlePrice(int totlePrice) {
		this.totlePrice = totlePrice;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Override
	public String toString() {
		return "AddCart [cartId=" + cartId + ", userEmail=" + userEmail + ", menuName=" + menuName + ", restoName="
				+ restoName + ", menuImage=" + menuImage + ", menuPrice=" + menuPrice + ", totlePrice=" + totlePrice
				+ ", sumOfTotlePrice=" + sumOfTotlePrice + ", menuQuantity=" + menuQuantity + "]";
	}
	
 
	
}
