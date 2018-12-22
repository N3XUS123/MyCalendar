drop table Aula if exists
drop table AUTHORITIES if exists
drop table Cuenta if exists
drop table Cuenta_reservas if exists
drop table Reserva if exists
create table Aula (id bigint generated by default as identity, nombre varchar(255) not null, primary key (id))
create table AUTHORITIES (id bigint generated by default as identity, authority varchar(255) not null, usuario varchar(255), primary key (id))
create table Cuenta (USERNAME varchar(255) not null, EMAIL varchar(255) not null, ENABLED boolean not null, PASSWORD varchar(255) not null, primary key (USERNAME))
create table Cuenta_reservas (Cuenta_USERNAME varchar(255) not null, reservas_id bigint not null, primary key (Cuenta_USERNAME, reservas_id))
create table Reserva (id bigint generated by default as identity, end timestamp not null, start timestamp not null, aula_id bigint, primary key (id))
alter table Cuenta_reservas add constraint UK_g0lsvocsgwa19kogxwwkqd7hx unique (reservas_id)
alter table AUTHORITIES add constraint FK9rlpa28ubay96cvlgdg7s4mnp foreign key (usuario) references Cuenta
alter table Cuenta_reservas add constraint FKa7ohbo7wowqllr000een6tuse foreign key (reservas_id) references Reserva
alter table Cuenta_reservas add constraint FK6c5gs8sxnjphp453e2n6ml58u foreign key (Cuenta_USERNAME) references Cuenta
alter table Reserva add constraint FKg3h3n0m8dqp0n1qhlui83haii foreign key (aula_id) references Aula