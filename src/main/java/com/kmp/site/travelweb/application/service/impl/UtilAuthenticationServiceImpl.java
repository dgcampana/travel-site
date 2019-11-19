package com.kmp.site.travelweb.application.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.kmp.site.travelweb.domain.service.UtilAuthenticationService;

@Service
public class UtilAuthenticationServiceImpl implements UtilAuthenticationService{

	@Override
	public String getRole(Authentication auth) {
		String role = "";
		for (GrantedAuthority authorities : auth.getAuthorities()) {
			role = authorities.getAuthority();
		}
		return role;
	}

	@Override
	public boolean isRootOrAgent(Authentication auth) {
		boolean isUserAdmin = false;
		String role = this.getRole(auth);
		if(role.equals("ROLE_ROOT") || role.equals("ROLE_AGENT")) {
			isUserAdmin = true;
		}
		return isUserAdmin;
	}

}
