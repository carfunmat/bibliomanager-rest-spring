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

import com.carlosfunalleras.rest.dto.AutorDTO;
import com.carlosfunalleras.rest.dto.AutorFilterDTO;
import com.carlosfunalleras.rest.exceptions.AutorDuplicadoException;
import com.carlosfunalleras.rest.exceptions.AutorNotFoundException;
import com.carlosfunalleras.rest.exceptions.NombreNullException;
import com.carlosfunalleras.rest.models.services.IAutorService;

@RestController
@RequestMapping("/api/autores")
@CrossOrigin(origins = { "http://localhost:4200", "*" })
public class AutorRestController {
	
	@Autowired
	private IAutorService autorService;
	
	@GetMapping("/listaAutores")
	public ResponseEntity<List<AutorDTO>> listaAutores() {

		ResponseEntity<List<AutorDTO>> res;

		List<AutorDTO> listaAutoresDto = autorService.findAll();

		if (listaAutoresDto.isEmpty()) {
			res = ResponseEntity.notFound().build();
		} else {
			res = ResponseEntity.ok(listaAutoresDto);
		}

		return res;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AutorDTO> mostrarAutor(@PathVariable Long id) {

		ResponseEntity<AutorDTO> res;

		try {
			AutorDTO autor = autorService.findById(id);
			res = new ResponseEntity<>(autor, HttpStatus.OK);
		} catch (AutorNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return res;
	}
	
	@PostMapping("/creaAutor")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<AutorDTO> creaAutor(@RequestBody AutorDTO autor) {

		ResponseEntity<AutorDTO> res;

		try {
			if (autor.getNombre() == null)
				throw new NombreNullException();

			if (Boolean.TRUE.equals(autorService.existeAutor(autor.getNombre())))
				throw new AutorDuplicadoException();

			AutorDTO a = autorService.save(autor);

			res = new ResponseEntity<>(a, HttpStatus.CREATED);
		} catch (NombreNullException | AutorDuplicadoException e) {
			res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return res;
	}
	
	@PostMapping("/autorFilter")
	public ResponseEntity<List<AutorDTO>> findAutoresFiltro(@RequestBody AutorFilterDTO autorFilterDto) {

		ResponseEntity<List<AutorDTO>> res;
		List<AutorDTO> listaAutoresDto = autorService.findAll(autorFilterDto);

		if (listaAutoresDto.isEmpty()) {
			res = ResponseEntity.noContent().build();
		} else {
			res = ResponseEntity.ok(listaAutoresDto);
		}

		return res;
	}
	
	@PostMapping("/autorFilterPage")
	public ResponseEntity<Page<AutorDTO>> findAutorFiltroPaginado(@RequestBody AutorFilterDTO autorFilterDto,
			Pageable pageable) {

		ResponseEntity<Page<AutorDTO>> res;
		Page<AutorDTO> listaAutoresDto = autorService.findAll(autorFilterDto, pageable);

		if (listaAutoresDto.isEmpty()) {
			res = ResponseEntity.noContent().build();
		} else {
			res = ResponseEntity.ok(listaAutoresDto);
		}

		return res;
	}
	
	@PutMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<AutorDTO> actualizaAutor(@RequestBody AutorDTO autor, @PathVariable Long id) {

		ResponseEntity<AutorDTO> res;

		try {
			if (autor.getNombre() == null)
				throw new NombreNullException();

			

			AutorDTO autorActual = autorService.findById(id);
			
			if (Boolean.TRUE.equals(autorService.existeAutor(autor.getNombre())) && !(autor.getNombre().equals(autorActual.getNombre())))
				throw new AutorDuplicadoException();

			autorActual.setNombre(autor.getNombre());
			
			if (autor.getFechaNacimiento() != null) {
				autorActual.setFechaNacimiento(autor.getFechaNacimiento());
			}
			
			if (autor.getNacionalidad() != null && autor.getNacionalidad().length() > 0) {
				autorActual.setNacionalidad(autor.getNacionalidad());
			}
			
			autorActual.setLibros(autor.getLibros());

			autorService.save(autorActual);

			res = new ResponseEntity<>(autorActual, HttpStatus.CREATED);

		} catch (AutorNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (NombreNullException | AutorDuplicadoException e) {
			res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return res;
	}
	
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<AutorDTO> delete(@PathVariable Long id) {

		ResponseEntity<AutorDTO> res;

		try {
			autorService.delete(id);

			res = new ResponseEntity<>(HttpStatus.OK);
		} catch (AutorNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res;
	}

}
