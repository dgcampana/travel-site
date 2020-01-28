package com.kmp.site.travelweb.application.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExperiencesController {
	
	@Value("${backend.services.api}")
	private String urlBackend;
	
	
	@GetMapping("detail")
	public ModelAndView detail( @RequestParam(required = false) String id ) {
		ModelAndView mv = new ModelAndView("site/experience");
		mv.addObject("urlBackend", this.urlBackend);
		mv.addObject("title", "Vive la aventura");
		return mv;
	}
	
	@GetMapping("search")
	public ModelAndView search( @RequestParam(required = false) String tag, 
								@RequestParam(required = false) String where, 
								@RequestParam(required = false) String category ) {
		ModelAndView mv = new ModelAndView("site/experience-grid");
		mv.addObject("urlBackend", this.urlBackend);
		mv.addObject("title", "Vive la aventura");
		return mv;
	}

}
