package com.salesianostriana.calendar.serviceImp;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.calendar.model.Aula;
import com.salesianostriana.calendar.repo.AulaRepository;
import com.salesianostriana.calendar.service.AulaService;

@Service
public class AulaServiceImp implements AulaService {
	
	@Autowired
	AulaRepository repo;
	
	@Override
	public Aula create(Aula a) {
		
		return repo.save(a);
	}
	
	@Override
	public Iterable<Aula> findAulasNoReservadas(LocalDate inicio, LocalDate fin){
		
		return repo.findAulasNoReservadas(inicio, fin);
	}
	
	@Override
	public Aula findOne(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	@Override
	public Iterable<Aula> findAll() {
		return repo.findAll();
	}
	
	@Override
	public Aula save(Aula aula) {
		return repo.save(aula);
	}
	
	@Override
	public Aula edit(Aula a) {
		return repo.save(a);
	}
	
	@Override
	public Aula delete(Aula a) {
		Aula deleteAul = repo.findById(a.getId()).orElse(null);
		if(deleteAul != null)
			repo.delete(a);
		
		return deleteAul;
	}
	
	@Override
	public int countAulas() {
		return repo.countAulas();
	}
}
