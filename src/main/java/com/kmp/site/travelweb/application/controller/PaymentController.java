package com.kmp.site.travelweb.application.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Secured({"ROLE_ROOT","ROLE_AGENT"})
public class PaymentController {
	
	@Value("${backend.services.api}")
	private String urlBackend;
	
	@GetMapping("/payment")
    public ModelAndView userPayments(Authentication auth) {
		ModelAndView mv = new ModelAndView("partner/payments");
		mv.addObject("urlBackend", this.urlBackend);
		
		return mv;
    }
}
