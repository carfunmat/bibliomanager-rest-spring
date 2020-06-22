package com.carlosfunalleras.rest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carlosfunalleras.rest.dto.AutorDTO;
import com.carlosfunalleras.rest.dto.AutorFilterDTO;
import com.carlosfunalleras.rest.exceptions.AutorNotFoundException;

public interface IAutorService {
	
	List<AutorDTO> findAll();
	
	AutorDTO findById(Long id) throws AutorNotFoundException;
	
	AutorDTO save(AutorDTO autor);
	
	void delete(Long id) throws AutorNotFoundException;
	
	Boolean existeAutor(String nombre);
	
	List<AutorDTO> findAll(AutorFilterDTO autorFilter);
	
	Page<AutorDTO> findAll(AutorFilterDTO autorFilter, Pageable pageable);

}
