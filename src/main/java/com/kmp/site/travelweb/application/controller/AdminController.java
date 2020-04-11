package com.kmp.site.travelweb.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	
	@GetMapping("/a-dashboard")
	public ModelAndView doWishlistInit() {
		return new ModelAndView("admin/index");
	}
	
	@GetMapping("/a-new-event")
	public ModelAndView doAddEvent() {
		return new ModelAndView("admin/add-event");
	}
	
	@GetMapping("/a-events")
	public ModelAndView doEvents() {
		return new ModelAndView("admin/events");
	}
	
	@GetMapping("/a-payments")
	public ModelAndView doPayments() {
		return new ModelAndView("admin/payments");
	}
	
}
