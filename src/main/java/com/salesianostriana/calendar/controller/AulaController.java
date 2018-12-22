package com.salesianostriana.calendar.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.calendar.model.Aula;
import com.salesianostriana.calendar.service.AulaService;
import com.salesianostriana.calendar.service.CuentaService;

@Controller
@RequestMapping("/admin")
public class AulaController {
	
	@Autowired
	private AulaService aulaService;
	
	@Autowired
	private CuentaService cuentaService;
	
	/*Show aulas */
	
	@GetMapping("/aulas")
	public String list(Model model, Principal principal) {
		model.addAttribute("usuarioLogueado", cuentaService.findFirstCuentaByUsername(principal.getName()));
		model.addAttribute("lista", aulaService.findAll());
		model.addAttribute("aula", new Aula());
		return "admin/aulas";
	}	
	
	/*Create aula*/
	
	@PostMapping("/addAula")
	public String addOrEditAula(@ModelAttribute Aula a) {
		aulaService.create(a);
		return "redirect:/admin/aulas";
	}
	
	/*Edit Aulas*/
	
	@GetMapping("aulas/edit/{id}")
	public String ediForm(@PathVariable("id") Long id, Model model) {
		Aula a = aulaService.findOne(id);
		if (a == null) 
			return "redirect:/admin/aulas";
		else
		model.addAttribute("aula", a);
		return "redirect:/admin/aulas";
	}
	
	/*Delete Aula*/
	
	@GetMapping("aulas/delete/{id}")
	public String deleteAula(@PathVariable("id") Long id, Model model) {
		Aula a = aulaService.findOne(id);
		if(a == null)
			return "redirect:/admin/aulas";
		aulaService.delete(a);
		return "redirect:/admin/aulas";
	}
	
	@GetMapping("/ajax/aulaEdit/{id}")
	public String ajaxAulaEdit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("aulaEdit", aulaService.findOne(id));
		return "/admin/aulas :: aulaEdit";
	}
	
}
