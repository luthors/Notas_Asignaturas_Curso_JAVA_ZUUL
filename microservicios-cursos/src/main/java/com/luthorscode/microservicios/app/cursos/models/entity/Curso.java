package com.luthorscode.microservicios.app.cursos.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.luthorscode.microservicios.commons.alumnos.models.entity.Alumno;
import com.luthorscode.microservicios.commons.examenes.models.entity.Examen;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "cursos")
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	private String name;
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Alumno> alumnos;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Examen> examenes; 
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}
	
	public Curso() {
		this.alumnos = new ArrayList<>();
		this.examenes = new ArrayList<>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	public void addAlumno(Alumno alumno) {
		this.alumnos.add(alumno);
	}
	public void removeAlumno(Alumno alumno) {
		this.alumnos.remove(alumno);
	}
	public List<Examen> getExamenes() {
		return examenes;
	}
	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}
	public void addExamen(Examen examen) {
		this.examenes.add(examen);
	}
	public void removeExamen(Examen examen) {
		this.examenes.remove(examen);
	}
}
