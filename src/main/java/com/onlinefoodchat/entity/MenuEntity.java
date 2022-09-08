package com.onlinefoodchat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MenuEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int menuId;
	private String menuName;
	private String menuPrice;
	private String clientemail;
	@Column(nullable = true)
	private String menuImage = "";

	public MenuEntity() {
		super();
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(String menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}

	public String getClientemail() {
		return clientemail;
	}

	public void setClientemail(String clientemail) {
		this.clientemail = clientemail;
	}

	@Override
	public String toString() {
		return "MenuEntity [menuId=" + menuId + ", menuName=" + menuName + ", menuPrice=" + menuPrice + ", clientemail="
				+ clientemail + ", menuImage=" + menuImage + "]";
	}

}
