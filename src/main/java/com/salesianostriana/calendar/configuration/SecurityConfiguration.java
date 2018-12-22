package com.salesianostriana.calendar.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.calendar.serviceImp.CuentaServiceImp;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// Servicio que nos permite interactuar con el
	// "almacén" de usuarios.
	@Autowired
	private CuentaServiceImp cuentaServiceImp;

	// Codificador para la contraseña. En este
	// caso usamos BCryptPasswordEncoder.
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

	// Configuración de la autenticación. En este caso,
	// indicamos que será a través de un servicio.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(cuentaServiceImp).passwordEncoder(passwordEncoder());
	}

	// Configuración de la autorización.
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off

		http.authorizeRequests()
				.antMatchers("/", "/js/**", "/css/**", "/img/**", "/webjars/**", "/vendor/**", "/fonts/**").permitAll()
				.antMatchers("/h2-console/**").permitAll() // Para permitir la consola de H2
				.antMatchers("/login*", "/registro*").permitAll() // Para permitir el acceso al formulario de login
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated() // El resto de peticiones, autenticadas.
				.and().formLogin().loginPage("/login") // Ruta del controlador del formulario de login
				.defaultSuccessUrl("/web") // Ruta de redirección en caso de éxito.
				.loginProcessingUrl("/login") // Ruta de procesamiento del formulario de login.
				.failureUrl("/login?error=true") // Ruta en caso de error de login.
				.and().logout().logoutSuccessUrl("/"); // por defecto POST a /logout

		http.csrf().disable();
		http.headers().frameOptions().disable();
		// @formatter:on

	}

}