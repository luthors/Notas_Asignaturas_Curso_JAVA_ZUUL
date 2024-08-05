package com.luthorscode.microservicios.app.respuestas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.luthorscode.microservicios.commons.alumnos.models.entity",
		"com.luthorscode.microservicios.commons.examenes.models.entity",
		"com.luthorscode.microservicios.app.respuestas.models.entity"})
public class MicroserviciosRespuestasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosRespuestasApplication.class, args);
	}

}
