package com.carlosfunalleras.rest.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.carlosfunalleras.rest.dto.RolDTO;
import com.carlosfunalleras.rest.models.entity.Rol;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RolDTOConverter {
	
	private final ModelMapper modelMapper;

	  public RolDTO convertToDto(Rol rol) {
	    return modelMapper.map(rol, RolDTO.class);
	  }

	  public Rol convertToRol(RolDTO rolDTO) {
	    return modelMapper.map(rolDTO, Rol.class);
	  }

}
