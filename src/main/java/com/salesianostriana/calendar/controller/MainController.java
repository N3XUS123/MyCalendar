package com.salesianostriana.calendar.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.calendar.model.Cuenta;
import com.salesianostriana.calendar.service.AulaService;
import com.salesianostriana.calendar.service.CuentaService;
import com.salesianostriana.calendar.service.ReservaService;

@Controller
public class MainController {

	@Autowired
	private CuentaService cuentaService;
	@Autowired
	private AulaService aulaService;
	@Autowired
	private ReservaService reservaService;

	@GetMapping({ "/", "/login" })
	public String root(Model model, Principal principal) {
		if (principal != null) {
			return "redirect:/web";
		} else {
			model.addAttribute("cuenta", new Cuenta());
			return "index";
		}
	}

	@GetMapping({ "/web", "/web/" })
	public String userIndex(Model model, Principal principal) {
		model.addAttribute("usuarioLogueado", cuentaService.findFirstCuentaByUsername(principal.getName()));
		return "web/index";
	}

	@GetMapping({ "/admin", "/admin/" })
	public String indexAdmin(Model model, Principal principal) {
		model.addAttribute("usuarioLogueado", cuentaService.findFirstCuentaByUsername(principal.getName()));
		model.addAttribute("numUsers", cuentaService.countUsers());
		model.addAttribute("numAulas", aulaService.countAulas());
		model.addAttribute("numReservas", reservaService.countReservas());
		model.addAttribute("numVerificados", cuentaService.countVerifies());
		return "/admin/index";
	}
	
	@PostMapping("/registro")
	public String registro(@ModelAttribute Cuenta cuenta) {
		cuentaService.save(cuenta);
		return "redirect:/";
	}

}
