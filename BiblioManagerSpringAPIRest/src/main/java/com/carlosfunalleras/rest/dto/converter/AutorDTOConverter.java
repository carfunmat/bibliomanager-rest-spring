package com.carlosfunalleras.rest.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.carlosfunalleras.rest.dto.AutorDTO;
import com.carlosfunalleras.rest.models.entity.Autor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AutorDTOConverter {

	private final ModelMapper modelMapper;

	public AutorDTO convertToDto(Autor autor) {
		return modelMapper.map(autor, AutorDTO.class);
	}

	public Autor convertToAutor(AutorDTO autorDTO) {
		return modelMapper.map(autorDTO, Autor.class);
	}

}
