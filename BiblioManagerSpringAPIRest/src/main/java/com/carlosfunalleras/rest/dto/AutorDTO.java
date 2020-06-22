package com.carlosfunalleras.rest.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorDTO {
	
	private Long id;
	private String nombre;
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	
	@JsonIgnore
	private List<LibroDTO> libros;

}
