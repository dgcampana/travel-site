package com.kmp.site.travelweb.domain.service;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface SessionService {

	String login(Model model, Principal principal, RedirectAttributes flash, String error);
}
