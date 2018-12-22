package com.salesianostriana.calendar.repo;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.calendar.model.Aula;

public interface AulaRepository extends JpaRepository<Aula, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM AULA LEFT JOIN RESERVA ON (AULA.ID = RESERVA.AULA_ID) WHERE (:inicio NOT BETWEEN FECHAINICIO AND FECHAFIN AND :fin NOT BETWEEN FECHAINICIO AND FECHAFIN AND NOT(:inicio <= FECHAINICIO AND :fin >= FECHAFIN)")
	Iterable<Aula> findAulasNoReservadas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

	@Query("select count(*) from Aula a")
	public int countAulas();
}
