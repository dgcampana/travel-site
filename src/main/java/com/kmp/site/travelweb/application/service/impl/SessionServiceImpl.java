package com.kmp.site.travelweb.application.service.impl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kmp.site.travelweb.domain.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {
	
	@Value("${backend.services.api}")
	private String urlBackend;

	@Override
	public String login(Model model, Principal principal, RedirectAttributes flash, String error) {
		model.addAttribute("urlBackend", this.urlBackend);
		if(principal != null) {
    	   flash.addFlashAttribute("info","Ya has iniciado sesión");
    	   return "redirect:/";
       }
       if(error != null ) {
    	   model.addAttribute("error", "usuario o contraseña inválida");
       }
    	return "login";
	}

}
