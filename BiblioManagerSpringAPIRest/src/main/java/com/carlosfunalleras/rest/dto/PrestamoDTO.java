package com.carlosfunalleras.rest.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoDTO {
	
	private Long id;
	private LibroDTO libro;
	private UsuarioDTO usuario;
	private LocalDate fechaInicio;
	private LocalDate fechaFinal;

}
