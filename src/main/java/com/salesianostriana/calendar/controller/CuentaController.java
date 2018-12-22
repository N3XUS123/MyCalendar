package com.salesianostriana.calendar.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.calendar.service.CuentaService;

@Controller
@RequestMapping("/admin/usuarios")
public class CuentaController {
	
	@Autowired
	private CuentaService cuentaService;
	
	@GetMapping({"/", ""})
	public String userList(Model model, Principal principal) {
		model.addAttribute("usuarioLogueado", cuentaService.findFirstCuentaByUsername(principal.getName()));
		model.addAttribute("lista", cuentaService.findAll());
		return "admin/usuarios";
	}
	
	@GetMapping({"/validar/{username}"})
	public String validateUser(Model model, @PathVariable("username") String username) {
		cuentaService.activarCuenta(cuentaService.findFirstCuentaByUsername(username));
		return "redirect:/admin/usuarios";
	}
	
}
