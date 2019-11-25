package com.kmp.site.travelweb.application.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Secured({"ROLE_ROOT","ROLE_AGENT"})
public class ExperiencesController {
	
	@Value("${backend.services.api}")
	private String urlBackend;
	
	@GetMapping("/experiences")
    public ModelAndView userExperiences(Authentication auth) {
		ModelAndView mv = new ModelAndView("partner/experiences");
		mv.addObject("urlBackend", this.urlBackend);
		
		return mv;
    }

}
