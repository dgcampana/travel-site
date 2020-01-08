package com.kmp.site.travelweb.application.service.impl;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kmp.site.travelweb.application.dto.GenericResponse;
import com.kmp.site.travelweb.application.dto.login.RequestCreateUser;
import com.kmp.site.travelweb.domain.client.UserClient;
import com.kmp.site.travelweb.domain.service.SessionService;
import com.kmp.site.travelweb.domain.service.UtilAuthenticationService;

@Service
public class SessionServiceImpl implements SessionService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private static final String URL_BACKEND = "urlBackend";
	
	@Value("${backend.services.api}")
	private String urlBackend;
	
	@Autowired
	private UtilAuthenticationService utilAuthenticationService;
	
	private UserClient userClient;

	@Override
	public String login(Model model, Principal principal, RedirectAttributes flash, String error) {
		model.addAttribute(URL_BACKEND, this.urlBackend);
		if(principal != null) {
    	   flash.addFlashAttribute("info","Ya has iniciado sesión");
    	   return "redirect:/";
       }
       if(error != null ) {
    	   model.addAttribute("error", "usuario o contraseña inválida");
       }
    	return "login";
	}

	@Override
	public String indexPartner(Authentication auth, Model model) {
		model.addAttribute(URL_BACKEND, this.urlBackend); 
		if(auth != null) {
			   if(utilAuthenticationService.isRootOrAgent(auth)) {
				   return "partner/index"; 
			   } else {
				   model.addAttribute("accessDenied", true);
				   model.addAttribute("accessDeniedMsg", "Lo sentimos, aÃºn no eres partner"); 
				   return "site/index"; 
			   }
		}
		return "login";
	}

	@Override
	public ModelAndView confirmMail(String token) {
		ModelAndView mv = new ModelAndView("email-confirm");
		try {
			GenericResponse response = userClient.userValidate(token);
			mv.addObject("message", response.getMessage());
		}catch (Exception e) {
			log.error("ocurrio un error al confirmar el mail con el token: {}", token);
			log.error("error: {}", e.getMessage());
		}
		return mv;
	}




}
