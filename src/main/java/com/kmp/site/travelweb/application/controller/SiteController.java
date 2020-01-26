package com.kmp.site.travelweb.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SiteController {

	
	@GetMapping("wishlist")
	public ModelAndView doWishlistInit() {
		return new ModelAndView("site/wishlist");
	}
	
}
