package com.carlosfunalleras.rest.models.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carlosfunalleras.rest.dto.AutorDTO;
import com.carlosfunalleras.rest.dto.AutorFilterDTO;
import com.carlosfunalleras.rest.dto.converter.AutorDTOConverter;
import com.carlosfunalleras.rest.exceptions.AutorNotFoundException;
import com.carlosfunalleras.rest.models.entity.Autor;
import com.carlosfunalleras.rest.models.repository.IAutorRepository;

@Service
public class AutorServiceImpl implements IAutorService {

	@Autowired
	private IAutorRepository autorRepository;
	
	@Autowired
	private AutorDTOConverter autorDTOConverter;
	
	@Override
	@Transactional(readOnly = true)
	public List<AutorDTO> findAll() {
		List<AutorDTO> res = new LinkedList<>();
		List<Autor> listaAutores = autorRepository.findAll();

		AutorDTO autorDTO;
		for (Autor a : listaAutores) {
			autorDTO = autorDTOConverter.convertToDto(a);
			res.add(autorDTO);
		}

		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public AutorDTO findById(Long id) throws AutorNotFoundException {
		Autor a = autorRepository.findById(id).orElseThrow(AutorNotFoundException::new);
		return autorDTOConverter.convertToDto(a);
	}

	@Override
	@Transactional
	public AutorDTO save(AutorDTO autorDto) {
		Autor autor = autorDTOConverter.convertToAutor(autorDto);
		autor = autorRepository.save(autor);
		autorDto = autorDTOConverter.convertToDto(autor);
		return autorDto;
	}

	@Override
	@Transactional
	public void delete(Long id) throws AutorNotFoundException {
		autorRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Boolean existeAutor(String nombre) {
		Boolean res = false;
		for (AutorDTO autor : findAll()) {
			if (autor.getNombre().equals(nombre)) {
				res = true;
				break;
			}
		}
		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public List<AutorDTO> findAll(AutorFilterDTO autorFilter) {
		List<AutorDTO> res = new LinkedList<>();
		Specification<Autor> sp = autorFilter.consultar();
		List<Autor> listaAutores = autorRepository.findAll(sp);

		AutorDTO autorDTO;
		for (Autor a : listaAutores) {
			autorDTO = autorDTOConverter.convertToDto(a);
			res.add(autorDTO);
		}

		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<AutorDTO> findAll(AutorFilterDTO autorFilter, Pageable pageable) {
		List<AutorDTO> listAutorDto = new ArrayList<>();
		Page<AutorDTO> res;
		Specification<Autor> sp = autorFilter.consultar();
		Page<Autor> listaAutores = autorRepository.findAll(sp, pageable);

		AutorDTO autorDTO;
		for (Autor a : listaAutores) {
			autorDTO = autorDTOConverter.convertToDto(a);
			listAutorDto.add(autorDTO);
		}

		res = new PageImpl<>(listAutorDto, pageable, listaAutores.getTotalElements());

		return res;
	}

}
