package com.salesianostriana.calendar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import com.salesianostriana.calendar.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	@Query("select c from Cuenta c JOIN FETCH c.authorities WHERE c.username = ?1")
	public UserDetails findUserWithAuthorities(String username);

	@Query("select count(*) from Cuenta c")
	public int countUsers();

	@Query("select count(*) from Cuenta c where c.enabled = false")
	public int countVerifies();

	public Cuenta findFirstCuentaByUsername(String username);
}
