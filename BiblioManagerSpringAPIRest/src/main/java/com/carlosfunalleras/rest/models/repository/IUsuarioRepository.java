package com.carlosfunalleras.rest.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosfunalleras.rest.models.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
	
	 Usuario findByUsername(String username);

	 List<Usuario> findAll(Specification<Usuario> s);

	 Page<Usuario> findAll(Specification<Usuario> s, Pageable p);

}
