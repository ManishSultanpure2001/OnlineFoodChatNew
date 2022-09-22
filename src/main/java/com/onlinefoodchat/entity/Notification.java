package com.onlinefoodchat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String status="panding";
	private String userMessage;
	private String clintMessage;
	private String restoName;
	private String readStatus="unread";
	private int identifyId;
	private int orderid;
	private int userDeleteStatus=0;
	private int clientDeleteStatus=0;
	
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getClintMessage() {
		return clintMessage;
	}

	public void setClintMessage(String clintMessage) {
		this.clintMessage = clintMessage;
	}

	public String getRestoName() {
		return restoName;
	}

	public void setRestoName(String restoName) {
		this.restoName = restoName;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public int getIdentifyId() {
		return identifyId;
	}

	public void setIdentifyId(int identifyId) {
		this.identifyId = identifyId;
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getUserDeleteStatus() {
		return userDeleteStatus;
	}

	public void setUserDeleteStatus(int userDeleteStatus) {
		this.userDeleteStatus = userDeleteStatus;
	}

	public int getClientDeleteStatus() {
		return clientDeleteStatus;
	}

	public void setClientDeleteStatus(int clientDeleteStatus) {
		this.clientDeleteStatus = clientDeleteStatus;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", status=" + status + ", userMessage=" + userMessage + ", clintMessage="
				+ clintMessage + ", restoName=" + restoName + ", readStatus=" + readStatus + ", identifyId="
				+ identifyId + ", orderid=" + orderid + ", userDeleteStatus=" + userDeleteStatus
				+ ", clientDeleteStatus=" + clientDeleteStatus + "]";
	}
	
	
}
