package com.salesianostriana.calendar.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.calendar.model.Aula;
import com.salesianostriana.calendar.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	
	@Query("select count(*) from Reserva r")
	public int countReservas();
	
	@Query("select r from Reserva r")
	List<Reserva> todasLasReservas();
	
	@Query("select r from Reserva r where r.start between ?1 and ?2")
	List<Reserva> findBetweenDates(LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	@Query("select r from Reserva r where r.aula = ?1")
	List<Reserva> solapan(Aula aula);
	
}

