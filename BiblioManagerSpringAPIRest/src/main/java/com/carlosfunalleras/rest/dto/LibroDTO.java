package com.carlosfunalleras.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibroDTO {
	
	private Long id;
	private String titulo;
	private Integer numeroPaginas;
	private AutorDTO autor;
	private UbicacionDTO ubicacion;
	
	@JsonIgnore
	private List<PrestamoDTO> prestamos;

}
