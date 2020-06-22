package com.carlosfunalleras.rest.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "autores")
@Data
@AllArgsConstructor
public class Autor implements Serializable{

	private static final long serialVersionUID = 4063696959105059062L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String nombre;

	@Column(name = "fecha_nacimiento")
	private LocalDate fechaNacimiento;
	
	private String nacionalidad;
	
	@OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Libro> libros;
	
	public Autor() {
		libros = new ArrayList<>();
	}
}
