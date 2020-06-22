package com.carlosfunalleras.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UbicacionDTO {
	
	private Long id;
	private Integer pasillo;
	private Integer fila;
	private String seccion;
	@JsonIgnore
	private List<LibroDTO> libros;

}
