package com.salesianostriana.calendar.controller;

import java.security.Principal;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebErrorController implements ErrorController {

	@GetMapping("/error")
	public String RedirectError(Principal principal) {
		if(principal != null) {
			return "redirect:/web";
		}
		return "redirect:/";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
