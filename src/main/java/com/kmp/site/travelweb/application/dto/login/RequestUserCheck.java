package com.kmp.site.travelweb.application.dto.login;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestUserCheck {

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("hashedCode")
	private String hashedCode;
	
	public RequestUserCheck() {
	}
	
	public RequestUserCheck(String email) {
		this.email = email;
	}
	
	public RequestUserCheck(String email, String hashedCode) {
		this.email = email;
		this.hashedCode = hashedCode;
	}
}
