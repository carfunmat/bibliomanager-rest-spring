package com.carlosfunalleras.rest.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosfunalleras.rest.models.entity.Libro;

public interface ILibroRepository extends JpaRepository<Libro, Long> {

	Libro findByTitulo(String titulo);

	List<Libro> findAll(Specification<Libro> s);

	Page<Libro> findAll(Specification<Libro> s, Pageable p);

}
