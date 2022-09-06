package com.onlinefoodchat.entity;

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
	private String menuImage;
	
	public MenuEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuEntity(int menuId, String menuName, String menuPrice, String menuImage) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuImage = menuImage;
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

	@Override
	public String toString() {
		return "MenuEntity [menuId=" + menuId + ", menuName=" + menuName + ", menuPrice=" + menuPrice + ", menuImage="
				+ menuImage + "]";
	}
	
	
		
}
