package com.luthorscode.microservicios.app.cursos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luthorscode.microservicios.app.cursos.clients.RespuestaFeignClient;
import com.luthorscode.microservicios.app.cursos.models.entity.Curso;
import com.luthorscode.microservicios.app.cursos.models.repository.CursoRepository;
import com.luthorscode.microservicios.commons.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService {

	@Autowired
	private RespuestaFeignClient feignClient;
	
	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
	}

	@Override
	public Iterable<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
		return feignClient.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
	}
	
}
