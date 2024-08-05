package com.luthorscode.microservicios.app.cursos.services;



import com.luthorscode.microservicios.app.cursos.models.entity.Curso;
import com.luthorscode.microservicios.commons.services.CommonService;

public interface CursoService extends CommonService<Curso> {
	public Curso findCursoByAlumnoId(Long id);
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
}
