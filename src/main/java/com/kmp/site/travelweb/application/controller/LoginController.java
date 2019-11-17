package com.kmp.site.travelweb.application.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kmp.site.travelweb.domain.service.SessionService;

@Controller
public class LoginController {
	
	@Autowired
	private SessionService sessionService;

    @GetMapping("/login")
    public String login(Model model, Principal principal, RedirectAttributes flash,
    					@RequestParam(value = "error", required = false) String error) {
       return sessionService.login(model, principal, flash, error);
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
}
