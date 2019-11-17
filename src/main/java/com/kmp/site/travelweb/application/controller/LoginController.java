package com.kmp.site.travelweb.application.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, Principal principal, RedirectAttributes flash,
    					@RequestParam(value = "error", required = false) String error) {
       if(principal != null) {
    	   flash.addFlashAttribute("info","Ya tienes una sesion iniciada");
    	   return "redirect:/";
       }
       if(error != null ) {
    	   model.addAttribute("error", "usuario o contraseña invalido.");
       }
    	return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
}
