package com.salesianostriana.calendar.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.calendar.formbean.ReservaPeriodica;
import com.salesianostriana.calendar.model.Cuenta;
import com.salesianostriana.calendar.model.Reserva;
import com.salesianostriana.calendar.service.AulaService;
import com.salesianostriana.calendar.service.CuentaService;
import com.salesianostriana.calendar.service.ReservaService;

@Controller
public class ReservaController {

	@Autowired
	private CuentaService cuentaService;
	@Autowired
	private ReservaService reservaService;
	@Autowired
	private AulaService aulaService;

	@GetMapping("/web/addReserva")
	public String addReserva(Model model, Principal principal) {
		model.addAttribute("reservaPeriodica", new ReservaPeriodica());
		model.addAttribute("listaAulas", aulaService.findAll());
		model.addAttribute("reserva", new Reserva());
		model.addAttribute("usuarioLogueado", cuentaService.findFirstCuentaByUsername(principal.getName()));
		return "web/reserve";
	}

	@PostMapping("/web/addReserva/{id}")
	public String ConfirmReserva(@ModelAttribute("reservaPeriodica") ReservaPeriodica reservaPeriodica, Model model, Principal principal) {
		Cuenta c = cuentaService.findFirstCuentaByUsername(principal.getName());
		Reserva r = reservaService.fromPeriodicaToNormal(reservaPeriodica);
		r.setAula(aulaService.findOne(reservaPeriodica.getAulaId()));
		if(r.getStart().isBefore(LocalDateTime.now())) {
			return "redirect:/web/addReserva?error=1";
		} else if (reservaService.solapan(r.getStart(), r.getEnd(), r.getAula()) != null) {
			return "redirect:/web/addReserva?error=1";
		}
		cuentaService.edit(c.addReserva(r));		
		Authentication auth = new UsernamePasswordAuthenticationToken(c, c.getPassword(), c.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		model.addAttribute("usuarioLogueado", c);
		return "redirect:/web";
	}
	
	@GetMapping("/web/editReserve/{id}")
	public String editarReserva(@ModelAttribute("id") Long id, Model model, Principal principal) {
		Reserva reserva = reservaService.findOneReserva(id);
		model.addAttribute("usuarioLogueado", cuentaService.findFirstCuentaByUsername(principal.getName()));		
		model.addAttribute("reservaPeriodica", reservaService.fromNormalToPeridoca(id));
		model.addAttribute("reserva", reserva);
		model.addAttribute("listaAulas", aulaService.findAll());
		return "/web/editReserve";
	}
	
	@PostMapping("/web/reserveEdited/{id}")
	public String ConfirmEditedReserve(@ModelAttribute("id") Long id, @ModelAttribute("reservaPeriodica") ReservaPeriodica reservaPeriodica, Model model, Principal principal) {
		Cuenta c = cuentaService.findFirstCuentaByUsername(principal.getName());
		Reserva r = reservaService.fromPeriodicaToNormalEdited(reservaPeriodica, id);
		r.setAula(aulaService.findOne(reservaPeriodica.getAulaId()));
		System.out.println(r);
		reservaService.edit(r);
		Authentication auth = new UsernamePasswordAuthenticationToken(c, c.getPassword(), c.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		model.addAttribute("usuarioLogueado", c);
		return "redirect:/web";
	}
	
	@GetMapping("/web/removeReserve/{id}")
	public String removeReserva(@PathVariable("id") Long id, Model model, Principal principal) {
		Cuenta c = cuentaService.findFirstCuentaByUsername(principal.getName());
		Reserva r = reservaService.findOneReserva(id);
		
		if(r == null)
			return "redirect:/web";
		cuentaService.edit(c.removeReserva(r));
		Authentication auth = new UsernamePasswordAuthenticationToken(c, c.getPassword(), c.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);		
		return "redirect:/web";
	}

}
