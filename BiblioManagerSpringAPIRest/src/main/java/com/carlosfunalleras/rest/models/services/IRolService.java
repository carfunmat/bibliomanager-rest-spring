package com.carlosfunalleras.rest.models.services;

import java.util.List;

import com.carlosfunalleras.rest.dto.RolDTO;
import com.carlosfunalleras.rest.exceptions.RolNotFoundException;

public interface IRolService {
	
	List<RolDTO> findAll();
	
	RolDTO findByNombre(String nombre) throws RolNotFoundException;

}
