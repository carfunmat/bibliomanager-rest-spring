package com.carlosfunalleras.rest.models.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosfunalleras.rest.dto.UsuarioDTO;
import com.carlosfunalleras.rest.dto.UsuarioFilterDTO;
import com.carlosfunalleras.rest.dto.converter.UsuarioDTOConverter;
import com.carlosfunalleras.rest.exceptions.UsuarioNotFoundException;
import com.carlosfunalleras.rest.models.entity.Rol;
import com.carlosfunalleras.rest.models.entity.Usuario;
import com.carlosfunalleras.rest.models.repository.IRolRepository;
import com.carlosfunalleras.rest.models.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private IUsuarioRepository usuarioRepository;
	@Autowired
	private IRolRepository rolRepository;
	@Autowired
	private UsuarioDTOConverter usuarioDTOConverter;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username);
		if (usuario == null) {
			logger.error("Error en el login: no existe el usuario " + username);
			throw new UsernameNotFoundException("Error en el login: no existe el usuario " + username);
		}

		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
				.peek(authority -> logger.info("Rol: " + authority.getAuthority())).collect(Collectors.toList());

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll() {
		List<UsuarioDTO> res = new LinkedList<>();
		List<Usuario> listaUsuarios = usuarioRepository.findAll();

		UsuarioDTO usuarioDTO;
		for (Usuario s : listaUsuarios) {
			usuarioDTO = usuarioDTOConverter.convertToDto(s);
			res.add(usuarioDTO);
		}

		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) throws UsuarioNotFoundException {
		Usuario s = usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
		return usuarioDTOConverter.convertToDto(s);
	}

	@Override
	@Transactional
	public UsuarioDTO save(UsuarioDTO usuarioDto) {
		Usuario usuario = usuarioDTOConverter.convertToUsuario(usuarioDto);
		List<Rol> listaRoles = new ArrayList<>();
		listaRoles.add(rolRepository.findAll().get(1));
		usuario.setRoles(listaRoles);
		String encodedPassword = passwordEncoder().encode(usuario.getPassword());
		usuario.setPassword(encodedPassword);
		// TODO que sea false el enabled por defecto y activar confirmacion por email
		usuario.setEnabled(true);
		usuario = usuarioRepository.save(usuario);
		usuarioDto = usuarioDTOConverter.convertToDto(usuario);
		return usuarioDto;
	}

	@Override
	@Transactional
	public void delete(Long id) throws UsuarioNotFoundException {
		rolRepository.deleteUsuarioRol(id);
		usuarioRepository.deleteById(id);
	}

	private BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Transactional
	public Boolean existeUsuario(String username) {
		Boolean res = false;
		for (UsuarioDTO usuario : findAll()) {
			if (usuario.getUsername().equals(username)) {
				res = true;
				break;
			}
		}
		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll(UsuarioFilterDTO usuarioFilter) {
		List<UsuarioDTO> res = new LinkedList<>();
		Specification<Usuario> sp = usuarioFilter.consultar();
		List<Usuario> listaUsuarios = usuarioRepository.findAll(sp);

		UsuarioDTO usuarioDTO;
		for (Usuario u : listaUsuarios) {
			usuarioDTO = usuarioDTOConverter.convertToDto(u);
			res.add(usuarioDTO);
		}

		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioDTO> findAll(UsuarioFilterDTO usuarioFilter, Pageable pageable) {
		List<UsuarioDTO> listUsuarioDto = new ArrayList<>();
		Page<UsuarioDTO> res;
		Specification<Usuario> sp = usuarioFilter.consultar();
		Page<Usuario> listaUsuarios = usuarioRepository.findAll(sp, pageable);

		UsuarioDTO usuarioDTO;
		for (Usuario u : listaUsuarios) {
			usuarioDTO = usuarioDTOConverter.convertToDto(u);
			listUsuarioDto.add(usuarioDTO);
		}

		res = new PageImpl<>(listUsuarioDto, pageable, listaUsuarios.getTotalElements());

		return res;
	}

}
