package com.onlinefoodchat.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = @UniqueConstraint(name="clientEmail",columnNames = "clientEmail"))
@Entity
public class ClientLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "client_id")
	private int id;
	private String clientEmail;
	private String clientPhone;
	private String clientPassword;
	private String clientPlan;
	private int otp;
	
	private String restoName;
	private Date startDate;
	private Date endDate;

	
	
	public ClientLogin() {
		super();
	}

	public ClientLogin(int id, String clientEmail, String clientPhone, String clientPassword, String clientPlan,
			int otp, String restoName, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.clientEmail = clientEmail;
		this.clientPhone = clientPhone;
		this.clientPassword = clientPassword;
		this.clientPlan = clientPlan;
		this.otp = otp;
		this.restoName = restoName;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getClientPassword() {
		return clientPassword;
	}

	public void setClientPassword(String clientPassword) {
		this.clientPassword = clientPassword;
	}

	public String getClientPlan() {
		return clientPlan;
	}

	public void setClientPlan(String clientPlan) {
		this.clientPlan = clientPlan;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public String getRestoName() {
		return restoName;
	}

	public void setRestoName(String restoName) {
		this.restoName = restoName;
	}

	@Override
	public String toString() {
		return "ClientLogin [id=" + id + ", clientEmail=" + clientEmail + ", clientPhone=" + clientPhone
				+ ", clientPassword=" + clientPassword + ", clientPlan=" + clientPlan + ", otp=" + otp + ", restoName="
				+ restoName + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}
