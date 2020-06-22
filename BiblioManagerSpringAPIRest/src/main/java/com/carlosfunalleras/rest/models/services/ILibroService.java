package com.carlosfunalleras.rest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carlosfunalleras.rest.dto.LibroDTO;
import com.carlosfunalleras.rest.dto.LibroFilterDTO;
import com.carlosfunalleras.rest.exceptions.LibroNotFoundException;

public interface ILibroService {
	
	List<LibroDTO> findAll();
	
	LibroDTO findById(Long id) throws LibroNotFoundException;
	
	LibroDTO save(LibroDTO libroDto);
	
	void delete(Long id) throws LibroNotFoundException;
	
	Boolean existeLibro(String titulo);
	
	List<LibroDTO> findAll(LibroFilterDTO libroFilter);
	
	Page<LibroDTO> findAll(LibroFilterDTO libroFilter, Pageable pageable);
}
