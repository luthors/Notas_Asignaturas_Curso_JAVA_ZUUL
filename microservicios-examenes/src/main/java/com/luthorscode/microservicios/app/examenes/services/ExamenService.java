package com.luthorscode.microservicios.app.examenes.services;

import java.util.List;

import com.luthorscode.microservicios.commons.examenes.models.entity.Asignatura;
import com.luthorscode.microservicios.commons.examenes.models.entity.Examen;
import com.luthorscode.microservicios.commons.services.CommonService;

public interface ExamenService extends CommonService<Examen> {
	public List<Examen> findByNombre(String term);
	public Iterable<Asignatura> findAllAsignaturas();
}
