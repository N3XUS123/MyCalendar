insert into cuenta(username, email, password,enabled) values('admin', 'admin@admin.com', '$2a$04$dJ5YWbG5dE0wuPaPZWQDHu2oM/K2luEFhxsztxM6jhNsaU4iT8lvO',true);
insert into authorities(usuario,authority) values('admin','ROLE_ADMIN');
	
insert into cuenta(username, email, password,enabled) values('user', 'user@admin.com', '$2a$10$qBFaTt1Hiox6AIp/xsrZ..6SaGVHYGK4abQhPQME2Z.PnlpaMiBMy',true);
insert into authorities(usuario,authority) values('user','ROLE_USER');

insert into aula(id, nombre) values(1, 'Informatica');
insert into reserva(id, aula_id, start, end) values(1, 1, '2018-10-15 06:00:00', '2018-10-15 07:00:00');
insert into cuenta_reservas(cuenta_username, reservas_id) values ('admin', 1);
insert into reserva(id, aula_id, start, end) values(2, 1, '2018-10-16 07:00:00', '2018-10-16 08:00:00');
insert into cuenta_reservas(cuenta_username, reservas_id) values ('admin', 2);