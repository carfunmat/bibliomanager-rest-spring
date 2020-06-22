package com.carlosfunalleras.rest.models.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosfunalleras.rest.dto.RolDTO;
import com.carlosfunalleras.rest.dto.converter.RolDTOConverter;
import com.carlosfunalleras.rest.exceptions.RolNotFoundException;
import com.carlosfunalleras.rest.models.entity.Rol;
import com.carlosfunalleras.rest.models.repository.IRolRepository;

@Service
public class RolServiceImpl implements IRolService {

	@Autowired
	private IRolRepository rolRepository;
	
	@Autowired
	private RolDTOConverter rolDTOConverter;
	
	@Override
	@Transactional(readOnly = true)
	public List<RolDTO> findAll() {
		List<RolDTO> res = new LinkedList<>();
		List<Rol> listaRoles = rolRepository.findAll();

		RolDTO rolDTO;
		for (Rol r : listaRoles) {
			rolDTO = rolDTOConverter.convertToDto(r);
			res.add(rolDTO);
		}

		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public RolDTO findByNombre(String nombre) throws RolNotFoundException {
		Rol r = rolRepository.findByNombre(nombre);
		return rolDTOConverter.convertToDto(r);
	}

}
