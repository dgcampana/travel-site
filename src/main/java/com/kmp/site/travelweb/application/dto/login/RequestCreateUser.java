package com.kmp.site.travelweb.application.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateUser {
	
	private String password;

	private String email;

	private String name;

	private String lastName;
}
