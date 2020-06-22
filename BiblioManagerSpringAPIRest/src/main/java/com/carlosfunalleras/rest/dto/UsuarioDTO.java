package com.carlosfunalleras.rest.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

	private Long id;
	private String username;
	private String password;
	private Boolean enabled;
	private String nombre;
	private String apellido;
	private String email;
	private List<RolDTO> roles;

}
