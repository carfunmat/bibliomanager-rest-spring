package com.carlosfunalleras.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosfunalleras.rest.dto.RolDTO;
import com.carlosfunalleras.rest.exceptions.RolNotFoundException;
import com.carlosfunalleras.rest.models.services.IRolService;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = { "http://localhost:4200", "*" })
public class RolRestController {

	@Autowired
	private IRolService rolService;

	@GetMapping("/listaRoles")
	public ResponseEntity<List<RolDTO>> index() {

		ResponseEntity<List<RolDTO>> res;

		List<RolDTO> listaRolesDto = rolService.findAll();

		if (listaRolesDto.isEmpty()) {
			res = ResponseEntity.notFound().build();
		} else {
			res = ResponseEntity.ok(listaRolesDto);
		}

		return res;
	}

	@GetMapping("/rolNombre/{nombreRol}")
	public ResponseEntity<RolDTO> findRol(@PathVariable String nombreRol) {

		ResponseEntity<RolDTO> res;
		try {

			RolDTO rolDto = rolService.findByNombre(nombreRol);

			res = ResponseEntity.ok(rolDto);
		} catch (RolNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return res;
	}

}
