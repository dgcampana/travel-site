package com.kmp.site.travelweb.application.service.impl;

import java.security.Principal;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kmp.site.travelweb.domain.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {

	@Override
	public String login(Model model, Principal principal, RedirectAttributes flash, String error) {
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
