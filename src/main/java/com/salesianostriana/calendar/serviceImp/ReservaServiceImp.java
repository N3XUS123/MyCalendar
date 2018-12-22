package com.salesianostriana.calendar.serviceImp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.calendar.formbean.ReservaPeriodica;
import com.salesianostriana.calendar.model.Aula;
import com.salesianostriana.calendar.model.Reserva;
import com.salesianostriana.calendar.repo.ReservaRepository;
import com.salesianostriana.calendar.service.ReservaService;

@Service
public class ReservaServiceImp implements ReservaService{

	@Autowired
	private ReservaRepository reservaRepository;

	public Reserva addReserva(Reserva reserva) {
		return reservaRepository.save(reserva);
	}

	@Override
	public Reserva findOneReserva(Long id) {

		return reservaRepository.findById(id).orElse(null);
	}

	@Override
	public Reserva edit(Reserva reserva) {

		return reservaRepository.save(reserva);
	}

	@Override
	public Reserva deleteReserva(Reserva reserva) {

		Reserva deleteRes = reservaRepository.findById(reserva.getId()).orElse(null);
		if (deleteRes != null)
			reservaRepository.delete(reserva);

		return deleteRes;
	}

	@Override
	public int countReservas() {
		return reservaRepository.countReservas();
	}

	@Override
	public List<Reserva> findBetweenDates(LocalDateTime start, LocalDateTime end) {
		return reservaRepository.findBetweenDates(start, end);
	}

	@Override
	public List<Reserva> findAll() {
		return reservaRepository.findAll();
	}

	private LocalDateTime formatFecha(String dia, String hora) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String fecha = dia + " " + hora;
		return LocalDateTime.parse(fecha, formatter);
	}

	private String formatToDay(LocalDateTime fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return fecha.format(formatter);
	}

	private String formatToTime(LocalDateTime fecha) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return fecha.format(formatter);
	}


	@Override
	public Reserva fromPeriodicaToNormal(ReservaPeriodica reservaPeriodica) {
		Reserva r = new Reserva();
		r.setStart(formatFecha(reservaPeriodica.getDia(), reservaPeriodica.getHoraInicio()));
		r.setEnd(formatFecha(reservaPeriodica.getDia(), reservaPeriodica.getHoraFin()));
		return r;
	}
	
	@Override
	public Reserva fromPeriodicaToNormalEdited(ReservaPeriodica reservaPeriodica, Long id) {
		Reserva r = new Reserva();
		r.setStart(formatFecha(reservaPeriodica.getDia(), reservaPeriodica.getHoraInicio()));
		r.setEnd(formatFecha(reservaPeriodica.getDia(), reservaPeriodica.getHoraFin()));
		r.setId(id);
		return r;
	}

	@Override
	public ReservaPeriodica fromNormalToPeridoca(Long id) {
		ReservaPeriodica r = new ReservaPeriodica();
		Reserva reserva = findOneReserva(id);
		r.setDia(formatToDay(reserva.getStart()));
		r.setHoraInicio(formatToTime(reserva.getStart()));
		r.setHoraFin(formatToTime(reserva.getEnd()));
		r.setAulaId(reserva.getAula().getId());
		return r;
	}
	
	@Override
	public Reserva solapan(LocalDateTime fechaInicio, LocalDateTime fechaFin, Aula aula) {
		List<Reserva> solapan = reservaRepository.solapan(aula);
		for (int i = 0; i < solapan.size(); i++) {
			if ((fechaInicio.isBefore(solapan.get(i).getStart()) && fechaFin.isAfter(solapan.get(i).getStart())) 					
					|| (fechaInicio.isBefore(solapan.get(i).getEnd()) && fechaFin.isAfter(solapan.get(i).getEnd()))
					|| (fechaInicio.isEqual(solapan.get(i).getStart())) || fechaFin.isEqual(solapan.get(i).getEnd())) {
				return solapan.get(i);
			}
		}
		return null;
	}

}
