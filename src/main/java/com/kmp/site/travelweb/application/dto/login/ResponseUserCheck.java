package com.kmp.site.travelweb.application.dto.login;

import lombok.Data;


@Data
public class ResponseUserCheck {
	private String name;
	private String email;
	private String password;
	private String role;
	private int code;
}
