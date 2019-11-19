package com.kmp.site.travelweb.domain.service;

import org.springframework.security.core.Authentication;

public interface UtilAuthenticationService {
	
	String getRole(Authentication auth);
	boolean isRootOrAgent(Authentication auth);

}
