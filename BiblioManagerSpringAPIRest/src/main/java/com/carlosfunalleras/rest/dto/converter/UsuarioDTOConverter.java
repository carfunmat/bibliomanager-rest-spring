package com.carlosfunalleras.rest.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.carlosfunalleras.rest.dto.UsuarioDTO;
import com.carlosfunalleras.rest.models.entity.Usuario;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioDTOConverter {

	private final ModelMapper modelMapper;

	public UsuarioDTO convertToDto(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioDTO.class);
	}

	public Usuario convertToUsuario(UsuarioDTO usuarioDTO) {
		return modelMapper.map(usuarioDTO, Usuario.class);
	}

}
