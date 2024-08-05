package com.luthorscode.microservicios.app.examenes.controllers;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luthorscode.microservicios.app.examenes.services.ExamenService;
import com.luthorscode.microservicios.commons.controllers.CommonController;
import com.luthorscode.microservicios.commons.examenes.models.entity.Examen;

import jakarta.validation.Valid;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return this.validar(result);
		}
		Optional<Examen> o = service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Examen examenDb = o.get();
		examenDb.setName(examen.getName());
//		List<Pregunta> preguntasEliminadas = new ArrayList<>();
//		examenDb.getPreguntas().forEach(pregunta -> {
//			if (!examen.getPreguntas().contains(pregunta)) {
//				preguntasEliminadas.add(pregunta);
//			}
//		}); 
//		preguntasEliminadas.forEach(pregunta -> {
//			examenDb.removePregunta(pregunta);
//		});  Ahora todo lo hacemos con streams
		examenDb.getPreguntas()
			.stream()
			.filter(p -> !examen.getPreguntas().contains(p))
			.forEach(examenDb::removePregunta);
		examenDb.setPreguntas(examen.getPreguntas());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term) {
		return ResponseEntity.ok(service.findByNombre(term));
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> listarAsignaturas() {
		return ResponseEntity.ok(service.findAllAsignaturas());
	}
}
				
				
				
				
				