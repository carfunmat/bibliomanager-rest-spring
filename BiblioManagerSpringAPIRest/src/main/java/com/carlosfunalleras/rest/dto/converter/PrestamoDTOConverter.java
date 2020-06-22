package com.carlosfunalleras.rest.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.carlosfunalleras.rest.dto.PrestamoDTO;
import com.carlosfunalleras.rest.models.entity.Prestamo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PrestamoDTOConverter {
	
	private final ModelMapper modelMapper;

	  public PrestamoDTO convertToDto(Prestamo prestamo) {
	    return modelMapper.map(prestamo, PrestamoDTO.class);
	  }

	  public Prestamo convertToPrestamo(PrestamoDTO prestamoDTO) {
	    return modelMapper.map(prestamoDTO, Prestamo.class);
	  }

}
