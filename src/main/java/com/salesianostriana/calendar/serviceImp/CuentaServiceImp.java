package com.salesianostriana.calendar.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.calendar.configuration.PasswordEncrypt;
import com.salesianostriana.calendar.model.Authorities;
import com.salesianostriana.calendar.model.Cuenta;
import com.salesianostriana.calendar.repo.CuentaRepository;
import com.salesianostriana.calendar.service.CuentaService;

@Service
public class CuentaServiceImp implements CuentaService, UserDetailsService {

	@Autowired
	CuentaRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = repo.findUserWithAuthorities(username);
		if (userDetails == null) {
			throw new UsernameNotFoundException("User not found");

		}
		return userDetails;

	}

	@Override
	public Iterable<Cuenta> findAll() {
		return repo.findAll();
	}

	@Override
	public Cuenta save(Cuenta cuenta) {
		cuenta.setPassword(PasswordEncrypt.encryptPassword(cuenta.getPassword()));
		cuenta.addRole(new Authorities("ROLE_USER", cuenta));
		return repo.save(cuenta);
	}

	@Override
	public Cuenta edit(Cuenta c) {
		return repo.save(c);
	}

	@Override
	public Cuenta activarCuenta(Cuenta c) {
		c.setEnabled(!c.isEnabled());
		return repo.save(c);
	}

	@Override
	public Cuenta findFirstCuentaByUsername(String username) {
		return repo.findFirstCuentaByUsername(username);
	}

	@Override
	public int countUsers() {
		return repo.countUsers();
	}

	@Override
	public int countVerifies() {
		return repo.countVerifies();
	}
}
