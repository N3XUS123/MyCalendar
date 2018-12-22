package com.salesianostriana.calendar.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.salesianostriana.calendar.model.Aula;

@Service
public interface AulaService {
	
	public Aula create(Aula a);
	
	public Iterable<Aula> findAulasNoReservadas(LocalDate inicio, LocalDate fin);
	
	public Aula findOne(Long id);
	
	public Iterable<Aula> findAll();
	
	public Aula save(Aula aula);
	
	public Aula edit(Aula a);
	
	public Aula delete(Aula a);
	
	public int countAulas();
	
}
