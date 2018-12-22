package com.salesianostriana.calendar.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.calendar.formbean.ReservaPeriodica;
import com.salesianostriana.calendar.model.Aula;
import com.salesianostriana.calendar.model.Reserva;

@Service
public interface ReservaService {

	public Reserva addReserva(Reserva reserva);

	public Reserva findOneReserva(Long id);

	public Reserva edit(Reserva reserva);

	public Reserva deleteReserva(Reserva reserva);

	public int countReservas();

	public List<Reserva> findBetweenDates(LocalDateTime start, LocalDateTime end);
	
	public List<Reserva> findAll();

	public Reserva fromPeriodicaToNormal(ReservaPeriodica reservaPeriodica);
	
	public Reserva fromPeriodicaToNormalEdited(ReservaPeriodica reservaPeriodica, Long id);

	public ReservaPeriodica fromNormalToPeridoca(Long id);
	
	public Reserva solapan(LocalDateTime fechaInicio, LocalDateTime fechaFin, Aula aula);

}
