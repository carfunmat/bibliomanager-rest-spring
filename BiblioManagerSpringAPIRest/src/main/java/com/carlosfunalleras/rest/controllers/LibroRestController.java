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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlosfunalleras.rest.dto.LibroDTO;
import com.carlosfunalleras.rest.dto.LibroFilterDTO;
import com.carlosfunalleras.rest.exceptions.LibroDuplicadoException;
import com.carlosfunalleras.rest.exceptions.LibroNotFoundException;
import com.carlosfunalleras.rest.exceptions.NombreNullException;
import com.carlosfunalleras.rest.exceptions.TituloNullException;
import com.carlosfunalleras.rest.models.services.ILibroService;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = { "http://localhost:4200", "*" })
public class LibroRestController {
	
	@Autowired
	private ILibroService libroService;
	
//	@Autowired
//	private IAutorService autorService;
	
	@GetMapping("/listaLibros")
	public ResponseEntity<List<LibroDTO>> listaLibros() {

		ResponseEntity<List<LibroDTO>> res;

		List<LibroDTO> listaLibrosDto = libroService.findAll();

		if (listaLibrosDto.isEmpty()) {
			res = ResponseEntity.notFound().build();
		} else {
			res = ResponseEntity.ok(listaLibrosDto);
		}

		return res;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LibroDTO> mostrarLibro(@PathVariable Long id) {

		ResponseEntity<LibroDTO> res;

		try {
			LibroDTO libro = libroService.findById(id);
			res = new ResponseEntity<>(libro, HttpStatus.OK);
		} catch (LibroNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return res;
	}
	
	@PostMapping("/creaLibro")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<LibroDTO> creaLibro(@RequestBody LibroDTO libro) {

		ResponseEntity<LibroDTO> res;

		try {
			if (libro.getTitulo() == null || libro.getTitulo().length() < 1)
				throw new NombreNullException();

			if (Boolean.TRUE.equals(libroService.existeLibro(libro.getTitulo())))
				throw new LibroDuplicadoException();
			
			

			LibroDTO l = libroService.save(libro);

			res = new ResponseEntity<>(l, HttpStatus.CREATED);
		} catch (NombreNullException | LibroDuplicadoException e) {
			res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return res;
	}
	
	@PostMapping("/libroFilter")
	public ResponseEntity<List<LibroDTO>> findLibrosFiltro(@RequestBody LibroFilterDTO libroFilterDto) {

		ResponseEntity<List<LibroDTO>> res;
		List<LibroDTO> listaLibrosDto = libroService.findAll(libroFilterDto);

		if (listaLibrosDto.isEmpty()) {
			res = ResponseEntity.noContent().build();
		} else {
			res = ResponseEntity.ok(listaLibrosDto);
		}

		return res;
	}
	
	@PostMapping("/libroFilterPage")
	public ResponseEntity<Page<LibroDTO>> findLibroFiltroPaginado(@RequestBody LibroFilterDTO libroFilterDto,
			Pageable pageable) {

		ResponseEntity<Page<LibroDTO>> res;
		Page<LibroDTO> listaLibrosDto = libroService.findAll(libroFilterDto, pageable);

		if (listaLibrosDto.isEmpty()) {
			res = ResponseEntity.noContent().build();
		} else {
			res = ResponseEntity.ok(listaLibrosDto);
		}

		return res;
	}
	
	@PutMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<LibroDTO> actualizaLibro(@RequestBody LibroDTO libro, @PathVariable Long id) {

		ResponseEntity<LibroDTO> res;

		try {
			if (libro.getTitulo() == null || libro.getTitulo().length() < 1)
				throw new TituloNullException();

			if (Boolean.TRUE.equals(libroService.existeLibro(libro.getTitulo())))
				throw new LibroDuplicadoException();
			
			// TODO comprobar si hay que comprobar existencia o no de autor al modificar libro
//			if (Boolean.FALSE.equals(autorService.existeAutor(libro.getAutor().getNombre()))) {
//				
//			}

			LibroDTO libroActual = libroService.findById(id);

			libroActual.setAutor(libro.getAutor());
			
			if (libro.getNumeroPaginas() != null && libro.getNumeroPaginas() > 0) {
				libroActual.setNumeroPaginas(libro.getNumeroPaginas());
			}
			
			libroActual.setPrestamos(libro.getPrestamos());
			libroActual.setTitulo(libro.getTitulo());
			
			if (libro.getUbicacion() != null) {
				libroActual.setUbicacion(libro.getUbicacion());
			}

			libroService.save(libroActual);

			res = new ResponseEntity<>(libroActual, HttpStatus.CREATED);

		} catch (LibroNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (TituloNullException | LibroDuplicadoException e) {
			res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return res;
	}
	
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<LibroDTO> delete(@PathVariable Long id) {

		ResponseEntity<LibroDTO> res;

		try {
			libroService.delete(id);

			res = new ResponseEntity<>(HttpStatus.OK);
		} catch (LibroNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res;
	}

}
