package com.onlinefoodchat.entity;

public class ReCaptchaResponse {
	private boolean success = true;
	private String chalenge_ts;
	private String hostName;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getChalenge_ts() {
		return chalenge_ts;
	}

	public void setChalenge_ts(String chalenge_ts) {
		this.chalenge_ts = chalenge_ts;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

}
