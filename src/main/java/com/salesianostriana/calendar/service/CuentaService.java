package com.salesianostriana.calendar.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.calendar.model.Cuenta;

@Service
public interface CuentaService {

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	public Iterable<Cuenta> findAll();

	public Cuenta save(Cuenta cuenta);

	public Cuenta edit(Cuenta c);
	
	public Cuenta activarCuenta(Cuenta c);

	public Cuenta findFirstCuentaByUsername(String username);

	public int countUsers();

	public int countVerifies();
	
}
