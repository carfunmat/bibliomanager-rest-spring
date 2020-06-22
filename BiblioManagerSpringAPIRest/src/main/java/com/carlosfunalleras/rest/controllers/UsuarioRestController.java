package com.carlosfunalleras.rest.controllers;

import java.util.ArrayList;
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

import com.carlosfunalleras.rest.dto.RolDTO;
import com.carlosfunalleras.rest.dto.UsuarioDTO;
import com.carlosfunalleras.rest.dto.UsuarioFilterDTO;
import com.carlosfunalleras.rest.exceptions.EmailNullException;
import com.carlosfunalleras.rest.exceptions.NombreNullException;
import com.carlosfunalleras.rest.exceptions.RolNotFoundException;
import com.carlosfunalleras.rest.exceptions.UsernameDuplicadoException;
import com.carlosfunalleras.rest.exceptions.UsernameNullException;
import com.carlosfunalleras.rest.exceptions.UsuarioNotFoundException;
import com.carlosfunalleras.rest.models.services.IRolService;
import com.carlosfunalleras.rest.models.services.IUsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = { "http://localhost:4200", "*" })
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IRolService rolService;

	@GetMapping("/listaUsuarios")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<List<UsuarioDTO>> listaUsuarios() {

		ResponseEntity<List<UsuarioDTO>> res;

		List<UsuarioDTO> listaUsuariosDto = usuarioService.findAll();

		if (listaUsuariosDto.isEmpty()) {
			res = ResponseEntity.notFound().build();
		} else {
			res = ResponseEntity.ok(listaUsuariosDto);
		}

		return res;
	}

	@GetMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<UsuarioDTO> mostrarUsuario(@PathVariable Long id) {

		ResponseEntity<UsuarioDTO> res;

		try {
			UsuarioDTO usuario = usuarioService.findById(id);
			res = new ResponseEntity<>(usuario, HttpStatus.OK);
		} catch (UsuarioNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return res;
	}

	@PostMapping("/creaUsuario")
	public ResponseEntity<UsuarioDTO> creaUsuario(@RequestBody UsuarioDTO usuario) {

		ResponseEntity<UsuarioDTO> res;

		try {
			if (usuario.getNombre() == null || usuario.getApellido() == null)
				throw new NombreNullException();
			if (usuario.getUsername() == null || usuario.getUsername().length() < 1)
				throw new UsernameNullException();

			if (usuario.getEmail() == null)
				throw new EmailNullException();

			if (Boolean.TRUE.equals(usuarioService.existeUsuario(usuario.getUsername())))
				throw new UsernameDuplicadoException();

			RolDTO rolPorDefecto = rolService.findByNombre("ROLE_USER");
			List<RolDTO> roles = new ArrayList<>();
			roles.add(rolPorDefecto);
			usuario.setRoles(roles);

			UsuarioDTO u = usuarioService.save(usuario);

			res = new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (NombreNullException | UsernameNullException | EmailNullException | UsernameDuplicadoException
				| RolNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return res;
	}

	@PostMapping("/usuarioFilter")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<List<UsuarioDTO>> findUsuariosFiltro(@RequestBody UsuarioFilterDTO usuarioFilterDto) {

		ResponseEntity<List<UsuarioDTO>> res;
		List<UsuarioDTO> listaUsuariosDto = usuarioService.findAll(usuarioFilterDto);

		if (listaUsuariosDto.isEmpty()) {
			res = ResponseEntity.noContent().build();
		} else {
			res = ResponseEntity.ok(listaUsuariosDto);
		}

		return res;
	}

	@PostMapping("/usuarioFilterPage")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Page<UsuarioDTO>> findUsuarioFiltroPaginado(@RequestBody UsuarioFilterDTO usuarioFilterDto,
			Pageable pageable) {

		ResponseEntity<Page<UsuarioDTO>> res;
		Page<UsuarioDTO> listaUsuariosDto = usuarioService.findAll(usuarioFilterDto, pageable);

		if (listaUsuariosDto.isEmpty()) {
			res = ResponseEntity.noContent().build();
		} else {
			res = ResponseEntity.ok(listaUsuariosDto);
		}

		return res;
	}

	@PutMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<UsuarioDTO> actualizaUsuario(@RequestBody UsuarioDTO usuario, @PathVariable Long id) {

		ResponseEntity<UsuarioDTO> res;

		try {
			if (usuario.getNombre() == null || usuario.getApellido() == null)
				throw new NombreNullException();
			if (usuario.getUsername() == null || usuario.getUsername().length() < 1)
				throw new UsernameNullException();

			if (usuario.getEmail() == null)
				throw new EmailNullException();

			

			UsuarioDTO usuarioActual = usuarioService.findById(id);
			
			if (Boolean.TRUE.equals(usuarioService.existeUsuario(usuario.getUsername())) && !(usuario.getUsername().equals(usuarioActual.getUsername())))
				throw new UsernameDuplicadoException();

			usuarioActual.setUsername(usuario.getUsername());
			usuarioActual.setPassword(usuario.getPassword());
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setApellido(usuario.getApellido());
			usuarioActual.setEmail(usuario.getEmail());
			usuarioActual.setEnabled(usuario.getEnabled());
			usuarioActual.setRoles(usuario.getRoles());

			usuarioService.save(usuarioActual);

			res = new ResponseEntity<>(usuarioActual, HttpStatus.CREATED);

		} catch (UsuarioNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (NombreNullException | UsernameNullException | EmailNullException | UsernameDuplicadoException e) {
			res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return res;
	}

	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<UsuarioDTO> delete(@PathVariable Long id) {

		ResponseEntity<UsuarioDTO> res;

		try {
			usuarioService.delete(id);

			res = new ResponseEntity<>(HttpStatus.OK);
		} catch (UsuarioNotFoundException e) {
			res = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return res;
	}

}
