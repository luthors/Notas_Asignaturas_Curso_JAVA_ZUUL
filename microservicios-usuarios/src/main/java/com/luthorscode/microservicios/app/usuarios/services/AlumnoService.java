package com.luthorscode.microservicios.app.usuarios.services;



import java.util.List;

import com.luthorscode.microservicios.commons.alumnos.models.entity.Alumno;
import com.luthorscode.microservicios.commons.services.CommonService;


public interface AlumnoService extends CommonService<Alumno> {
	public List<Alumno> findByNombreOrApellido(String term);
}