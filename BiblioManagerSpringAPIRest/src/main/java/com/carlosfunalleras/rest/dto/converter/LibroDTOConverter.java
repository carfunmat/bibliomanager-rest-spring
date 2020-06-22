package com.carlosfunalleras.rest.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.carlosfunalleras.rest.dto.LibroDTO;
import com.carlosfunalleras.rest.models.entity.Libro;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LibroDTOConverter {

	private final ModelMapper modelMapper;

	public LibroDTO convertToDto(Libro libro) {
		return modelMapper.map(libro, LibroDTO.class);
	}

	public Libro convertToLibro(LibroDTO libroDTO) {
		return modelMapper.map(libroDTO, Libro.class);
	}

}
