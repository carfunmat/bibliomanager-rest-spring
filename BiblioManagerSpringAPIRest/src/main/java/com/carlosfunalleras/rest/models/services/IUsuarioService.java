package com.carlosfunalleras.rest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.carlosfunalleras.rest.dto.UsuarioDTO;
import com.carlosfunalleras.rest.dto.UsuarioFilterDTO;
import com.carlosfunalleras.rest.exceptions.UsuarioNotFoundException;
import com.carlosfunalleras.rest.models.entity.Usuario;

public interface IUsuarioService extends UserDetailsService {

	Usuario findByUsername(String username);
	
	List<UsuarioDTO> findAll();
	
	UsuarioDTO findById(Long id) throws UsuarioNotFoundException;
	
	UsuarioDTO save(UsuarioDTO usuario);
	
	void delete(Long id) throws UsuarioNotFoundException;
	
	Boolean existeUsuario(String username);
	
	List<UsuarioDTO> findAll(UsuarioFilterDTO usuarioFilter);
	
	Page<UsuarioDTO> findAll(UsuarioFilterDTO usuarioFilter, Pageable pageable);
	
}
