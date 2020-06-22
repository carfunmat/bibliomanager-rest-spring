package com.carlosfunalleras.rest.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.carlosfunalleras.rest.dto.UbicacionDTO;
import com.carlosfunalleras.rest.models.entity.Ubicacion;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UbicacionDTOConverter {
	
	private final ModelMapper modelMapper;

	  public UbicacionDTO convertToDto(Ubicacion ubicacion) {
	    return modelMapper.map(ubicacion, UbicacionDTO.class);
	  }

	  public Ubicacion convertToUbicacion(UbicacionDTO ubicacionDTO) {
	    return modelMapper.map(ubicacionDTO, Ubicacion.class);
	  }

}
