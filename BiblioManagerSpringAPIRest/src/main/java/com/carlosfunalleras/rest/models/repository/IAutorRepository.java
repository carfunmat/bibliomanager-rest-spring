package com.carlosfunalleras.rest.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosfunalleras.rest.models.entity.Autor;

public interface IAutorRepository extends JpaRepository<Autor, Long> {

	Autor findByNombre(String nombre);

	List<Autor> findAll(Specification<Autor> s);

	Page<Autor> findAll(Specification<Autor> s, Pageable p);

}
