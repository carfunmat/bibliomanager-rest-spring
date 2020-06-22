package com.carlosfunalleras.rest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carlosfunalleras.rest.dto.PrestamoDTO;
import com.carlosfunalleras.rest.dto.PrestamoFilterDTO;
import com.carlosfunalleras.rest.exceptions.PrestamoNotFoundException;

public interface IPrestamoService {
	
	List<PrestamoDTO> findAll();
	
	PrestamoDTO findById(Long id) throws PrestamoNotFoundException;
	
	PrestamoDTO save(PrestamoDTO prestamoDto);
	
	void delete(Long id) throws PrestamoNotFoundException;
	
	List<PrestamoDTO> findAll(PrestamoFilterDTO prestamoFilter);
	
	Page<PrestamoDTO> findAll(PrestamoFilterDTO prestamoFilter, Pageable pageable);

}
