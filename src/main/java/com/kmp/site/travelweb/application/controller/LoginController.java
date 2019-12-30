package com.kmp.site.travelweb.application.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kmp.site.travelweb.domain.service.SessionService;

@Controller
public class LoginController {
	
	@Autowired
	private SessionService sessionService;
	
	@Value("${backend.services.api}")
	private String urlBackend;
	
	@GetMapping("/")
    public ModelAndView indexSite() {
		ModelAndView mv = new ModelAndView("site/index");	
        mv.addObject("urlBackend", this.urlBackend);
        return mv;
    }

    @GetMapping("/login")
    public String login(Model model, Principal principal, RedirectAttributes flash, @RequestParam(value = "error", required = false) String error) {
       return sessionService.login(model, principal, flash, error);
    }
    
    @GetMapping("/register")
    public ModelAndView register(Model model) {
       ModelAndView mv = new ModelAndView("register");	
       mv.addObject("urlBackend", this.urlBackend);
       return mv;
    }
    
    @GetMapping("/session/confirmation-email")
	public ModelAndView confirmMail(@RequestParam("secure") String token) {
		return sessionService.confirmMail(token);
	}
    
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
    
    
}
