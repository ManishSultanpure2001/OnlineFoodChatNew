package com.onlinefoodchat.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClientLogin {
		@Id
		public String clientEmail;
		public String clientPhone;
		public String clientPassword;
		public String clientPlan;
		public Date startDate;
		public Date endDate;
		public ClientLogin() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ClientLogin(String clientEmail, String clientPhone, String clientPassword, String clientPlan,
				Date startDate, Date endDate) {
			super();
			this.clientEmail = clientEmail;
			this.clientPhone = clientPhone;
			this.clientPassword = clientPassword;
			this.clientPlan = clientPlan;
			this.startDate = startDate;
			this.endDate = endDate;
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
		@Override
		public String toString() {
			return "LoginEntity [clientEmail=" + clientEmail + ", clientPhone=" + clientPhone + ", clientPassword="
					+ clientPassword + ", clientPlan=" + clientPlan + ", startDate=" + startDate + ", endDate="
					+ endDate + "]";
		}
		
		
}
