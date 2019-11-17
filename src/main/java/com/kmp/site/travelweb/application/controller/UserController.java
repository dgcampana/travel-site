package com.kmp.site.travelweb.application.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping({"/", "/dashboard"})
    public String userIndex(Principal principal) {
		return "user/index";
    }
	
	
}
