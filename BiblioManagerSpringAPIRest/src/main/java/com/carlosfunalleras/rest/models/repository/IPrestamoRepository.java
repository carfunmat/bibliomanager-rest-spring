package com.carlosfunalleras.rest.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carlosfunalleras.rest.models.entity.Prestamo;

public interface IPrestamoRepository extends JpaRepository<Prestamo, Long> {

	Prestamo findByUsuario(String username);

	List<Prestamo> findAll(Specification<Prestamo> s);

	Page<Prestamo> findAll(Specification<Prestamo> s, Pageable p);

}
