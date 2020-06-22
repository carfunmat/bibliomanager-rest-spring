package com.carlosfunalleras.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosfunalleras.rest.dto.PrestamoDTO;
import com.carlosfunalleras.rest.dto.PrestamoFilterDTO;
import com.carlosfunalleras.rest.exceptions.LibroNotFoundException;
import com.carlosfunalleras.rest.exceptions.PrestamoNotFoundException;
import com.carlosfunalleras.rest.exceptions.UsuarioNotFoundException;
import com.carlosfunalleras.rest.models.services.IPrestamoService;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = { "http://localhost:4200", "*" })
public class PrestamoRestController {
	
	@Autowired
	private IPrestamoService prestamoService;
	
	@GetMapping("/listaPrestamos")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<List<PrestamoDTO>> listaPrestamos() {

		ResponseEntity<List<PrestamoDTO>> res;

		List<PrestamoDTO> listaPrestamosDto = prestamoService.findAll();

		if (listaPrestamosDto.isEmpty()) {
			res = ResponseEntity.notFound().build();
		} else {
			res = ResponseEntity.ok(listaPrestamosDto);
		}

		return res;
	}
	
	@GetMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<PrestamoDTO> mostrarPrestamo(@PathVariable Long id) {

		ResponseEntity<PrestamoDTO> res;

		try {
			PrestamoDTO prestamo = prestamoService.findById(id);
			res = new ResponseEntity<>(prestamo, HttpStatus.OK);
		} catch (PrestamoNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return res;
	}
	
	@PostMapping("/creaPrestamo")
	public ResponseEntity<PrestamoDTO> creaPrestamo(@RequestBody PrestamoDTO prestamo) {

		ResponseEntity<PrestamoDTO> res;

		try {
//			if (prestamo.getFechaFinal().isBefore(prestamo.getFechaInicio())
//					|| prestamo.getFechaFinal().equals(prestamo.getFechaInicio())
//					|| prestamo.getFechaInicio() == null
//					|| prestamo.getFechaFinal() == null) {
//				throw new FechaIncorrectaException();
//			}
			
			if (prestamo.getLibro() == null) {
				throw new LibroNotFoundException();
			}
			
			if (prestamo.getUsuario() == null) {
				throw new UsuarioNotFoundException();
			}

			PrestamoDTO p = prestamoService.save(prestamo);

			res = new ResponseEntity<>(p, HttpStatus.CREATED);
//		} catch (FechaIncorrectaException e) {
//			res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (LibroNotFoundException | UsuarioNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return res;
	}
	
	@PostMapping("/prestamoFilter")
	public ResponseEntity<List<PrestamoDTO>> findPrestamosFiltro(@RequestBody PrestamoFilterDTO prestamoFilterDto) {

		ResponseEntity<List<PrestamoDTO>> res;
		List<PrestamoDTO> listaPrestamosDto = prestamoService.findAll(prestamoFilterDto);

		if (listaPrestamosDto.isEmpty()) {
			res = ResponseEntity.noContent().build();
		} else {
			res = ResponseEntity.ok(listaPrestamosDto);
		}

		return res;
	}
	
	@PostMapping("/prestamoFilterPage")
	public ResponseEntity<Page<PrestamoDTO>> findPrestamoFiltroPaginado(@RequestBody PrestamoFilterDTO prestamoFilterDto,
			Pageable pageable) {

		ResponseEntity<Page<PrestamoDTO>> res;
		Page<PrestamoDTO> listaPrestamosDto = prestamoService.findAll(prestamoFilterDto, pageable);

		if (listaPrestamosDto.isEmpty()) {
			res = ResponseEntity.noContent().build();
		} else {
			res = ResponseEntity.ok(listaPrestamosDto);
		}

		return res;
	}
	
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<PrestamoDTO> delete(@PathVariable Long id) {

		ResponseEntity<PrestamoDTO> res;

		try {
			prestamoService.delete(id);

			res = new ResponseEntity<>(HttpStatus.OK);
		} catch (PrestamoNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res;
	}

}
