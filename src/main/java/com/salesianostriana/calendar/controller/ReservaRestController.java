package com.salesianostriana.calendar.controller;

import java.util.Set;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesianostriana.calendar.model.Cuenta;
import com.salesianostriana.calendar.model.Reserva;

@RestController
public class ReservaRestController {

	@GetMapping("/reservelist")
	public Set<Reserva> showCalendar(@AuthenticationPrincipal Cuenta usuarioLogueado) {
		return usuarioLogueado.getReservas();
	}

}
