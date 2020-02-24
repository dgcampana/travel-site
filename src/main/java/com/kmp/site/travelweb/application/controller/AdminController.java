package com.kmp.site.travelweb.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	
	@GetMapping("/admin-panel")
	public ModelAndView doWishlistInit() {
		return new ModelAndView("admin/index");
	}
	
	@GetMapping("/add-experience")
	public ModelAndView doAddExperience() {
		return new ModelAndView("admin/add-experience");
	}
	
}
