package com.salesianostriana.calendar.configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncrypt {

	private static PasswordEncoder encoder = new BCryptPasswordEncoder();

	public static String encryptPassword(String password) {
		String encryptedValue = encoder.encode(password);
		encoder.matches(password, encryptedValue);
		return encryptedValue;
	}

}