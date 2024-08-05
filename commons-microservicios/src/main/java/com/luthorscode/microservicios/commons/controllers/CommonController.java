package com.luthorscode.microservicios.commons.controllers;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import com.luthorscode.microservicios.commons.services.CommonService;

public class CommonController<E,S extends CommonService<E>> {

	@Autowired
    protected S service;


    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok().body(service.findAll());
    }   
    
	@GetMapping("/pagina")
    public ResponseEntity<?> listar(Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id){
        Optional<E> body = service.findById(id);
        if (body.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(body.get());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody E entity, BindingResult result){
    	if(result.hasErrors()) {
	    	return this.validar(result);
    	}
        E entityDb = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityDb);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    protected ResponseEntity<?> validar(BindingResult result){
		//Map<String, Object> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		Map<String, Object> errors = new HashMap<>();
		result.getFieldErrors().forEach(error -> {
			errors.put(error.getField(),
						"El Campo "
						.concat(error.getField())
						.concat(" ")
						.concat(error.getDefaultMessage())
						);
		});
		return ResponseEntity.badRequest().body(errors);    	
	}
    
    
}