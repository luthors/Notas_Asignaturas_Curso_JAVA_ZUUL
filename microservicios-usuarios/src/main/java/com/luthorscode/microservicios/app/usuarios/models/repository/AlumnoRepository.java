package com.luthorscode.microservicios.app.usuarios.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.luthorscode.microservicios.commons.alumnos.models.entity.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
	@Query("SELECT a FROM Alumno a WHERE a.name like %?1% or a.lastName like %?1%")
	public List<Alumno> findByNombreOrApellido(String term);
}
