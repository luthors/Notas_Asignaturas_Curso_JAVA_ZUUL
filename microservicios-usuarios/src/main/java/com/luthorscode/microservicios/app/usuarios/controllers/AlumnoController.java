package com.luthorscode.microservicios.app.usuarios.controllers;
import java.io.IOException;
import java.util.Optional;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.luthorscode.microservicios.app.usuarios.services.AlumnoService;
import com.luthorscode.microservicios.commons.alumnos.models.entity.Alumno;
import com.luthorscode.microservicios.commons.controllers.CommonController;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class AlumnoController extends CommonController<Alumno, AlumnoService> {
    
	@GetMapping("/upload/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id) {
		Optional<Alumno> o = service.findById(id);
		if(o.isEmpty() || o.get().getPicture() == null) {
			return ResponseEntity.notFound().build();
		}
		Resource imagen= new ByteArrayResource(o.get().getPicture());
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
	}
			
	
	@PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
	        return this.validar(result);
        }
		
		Optional<Alumno> alumnoExist = service.findById(id);
        if (alumnoExist.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Alumno alumnoDb = alumnoExist.get();
        alumnoDb.setName(alumno.getName());
        alumnoDb.setLastName(alumno.getLastName());
        alumnoDb.setEmail(alumno.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
    }   
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable String term) {
		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}

	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearWithPicture(@Valid Alumno alumno, BindingResult result, 
			@RequestParam MultipartFile archivo) throws IOException {
		if (result.hasErrors()) {
			return this.validar(result);
		}
		if (!archivo.isEmpty()) {
			alumno.setPicture(archivo.getBytes());
		}
		return super.crear(alumno, result);
	}
	
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarWithPicture(@Valid Alumno alumno, BindingResult result, 
			@RequestParam MultipartFile archivo, @PathVariable Long id) throws IOException {
		if (result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Alumno> o = service.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnoDb = o.get();
		alumnoDb.setName(alumno.getName());
		alumnoDb.setLastName(alumno.getLastName());
		alumnoDb.setEmail(alumno.getEmail());
		if (!archivo.isEmpty()) {
			alumnoDb.setPicture(archivo.getBytes());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnoDb));
	}
	
	
}