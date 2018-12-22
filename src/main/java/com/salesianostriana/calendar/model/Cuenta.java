package com.salesianostriana.calendar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Cuenta implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USERNAME")
	private String username;

	@Column(name = "EMAIL", nullable = false)
	private String email;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "ENABLED", nullable = false)
	private boolean enabled;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cuenta")
	private Set<Authorities> authorities = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<Reserva> reservas = new HashSet<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}

	public Set<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public Cuenta addRole(Authorities a) {
		if (a != null) {
			a.setCuenta(this);
			this.authorities.add(a);
		}
		return this;
	}

	public Cuenta removeRole(Authorities a) {
		if (a != null) {
			a.setCuenta(null);
		}
		return this;
	}
	
	public Cuenta addReserva(Reserva r) {
		if (r != null) {
			this.reservas.add(r);
		}
		return this;
	}
	
	public Cuenta removeReserva(Reserva r) {
		if (r != null) {
			this.reservas.remove(r);
		}
		return this;
	}
	
}
