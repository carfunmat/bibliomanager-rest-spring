package com.carlosfunalleras.rest.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.carlosfunalleras.rest.exceptions.RolNotFoundException;
import com.carlosfunalleras.rest.models.entity.Rol;

public interface IRolRepository extends JpaRepository<Rol, Long> {
	
	List<Rol> findAll();

	Rol findByNombre(String nombre) throws RolNotFoundException;

	@Modifying
	@Query(value= "delete from usuarios_roles where usuario_id=?", nativeQuery=true)
	void deleteUsuarioRol(Long id);

}
