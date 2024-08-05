package com.luthorscode.microservicios.app.respuestas.services;

import com.luthorscode.microservicios.app.respuestas.models.entity.Respuesta;

public interface RespuestaService {
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenId);
	public Iterable<Respuesta> findExameenesIdsConRespuestaByAlumno(Long alumnoId);
}
