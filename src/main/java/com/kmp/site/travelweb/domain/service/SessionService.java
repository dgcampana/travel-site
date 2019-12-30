package com.kmp.site.travelweb.domain.service;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kmp.site.travelweb.application.dto.GenericResponse;
import com.kmp.site.travelweb.application.dto.login.RequestCreateUser;

public interface SessionService {

	String login(Model model, Principal principal, RedirectAttributes flash, String error);
	String indexPartner(Authentication auth, Model model);
	ModelAndView confirmMail(String token);

}
