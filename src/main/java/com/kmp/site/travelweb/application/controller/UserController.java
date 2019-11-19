package com.kmp.site.travelweb.application.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Secured({"ROLE_ROOT","ROLE_AGENT"})
public class UserController {

	
	@GetMapping("/dashboard")
    public String userIndex(Authentication auth) {
		return "partner/index";
    }
	
	@GetMapping("/experiences")
    public String userExperiences(Authentication auth) {
		return "partner/experiences";
    }
	
	@GetMapping("/add-on")
    public String userAddons(Authentication auth) {
		return "partner/add-ons";
    }
	
	@GetMapping("/payment")
    public String userPayments(Authentication auth) {
		return "partner/payments";
    }
	
	
}
