package com.kmp.site.travelweb.application.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kmp.site.travelweb.domain.service.SessionService;
import com.kmp.site.travelweb.domain.service.UtilAuthenticationService;

@Controller
public class LoginController {
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private UtilAuthenticationService utilAuthenticationService;
	
	@Value("${backend.services.api}")
	private String urlBackend;
	
	@GetMapping("/")
    public String login(Authentication auth, Model model) {
	   if(auth != null) {
		   auth.getAuthorities();
		   if(utilAuthenticationService.isRootOrAgent(auth)) {
			   return "partner/index"; 
		   } else {
			   model.addAttribute("accessDenied", true);
			   model.addAttribute("accessDeniedMsg", "Lo sentimos, aún no eres partner"); 
		   }
	   }
	   model.addAttribute("urlBackend", this.urlBackend);
	   return "login";
    }

    @GetMapping("/login")
    public String login(Model model, Principal principal, RedirectAttributes flash, @RequestParam(value = "error", required = false) String error) {
       return sessionService.login(model, principal, flash, error);
    }
    
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
    
    
}
