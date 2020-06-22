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

import com.carlosfunalleras.rest.dto.LibroDTO;
import com.carlosfunalleras.rest.dto.LibroFilterDTO;
import com.carlosfunalleras.rest.dto.converter.LibroDTOConverter;
import com.carlosfunalleras.rest.exceptions.LibroNotFoundException;
import com.carlosfunalleras.rest.models.entity.Libro;
import com.carlosfunalleras.rest.models.repository.ILibroRepository;

@Service
public class LibroServiceImpl implements ILibroService {

	@Autowired
	private ILibroRepository libroRepository;
	
	@Autowired
	private LibroDTOConverter libroDTOConverter;
//	@Autowired
//	private AutorDTOConverter autorDTOConverter;
//	@Autowired
//	private UbicacionDTOConverter ubicacionDTOConverter;
	
	@Override
	@Transactional(readOnly = true)
	public List<LibroDTO> findAll() {
		List<LibroDTO> res = new LinkedList<>();
		List<Libro> listaLibros = libroRepository.findAll();

		LibroDTO libroDTO;
		for (Libro l : listaLibros) {
			libroDTO = libroDTOConverter.convertToDto(l);
			res.add(libroDTO);
		}

		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public LibroDTO findById(Long id) throws LibroNotFoundException {
		Libro a = libroRepository.findById(id).orElseThrow(LibroNotFoundException::new);
		return libroDTOConverter.convertToDto(a);
	}

	@Override
	@Transactional
	public LibroDTO save(LibroDTO libroDto) {
		Libro libro = libroDTOConverter.convertToLibro(libroDto);
//		libro.setAutor(autorDTOConverter.convertToAutor(libroDto.getAutor()));
//		libro.setUbicacion(ubicacionDTOConverter.convertToUbicacion(libroDto.getUbicacion()));
		libro = libroRepository.save(libro);
		libroDto = libroDTOConverter.convertToDto(libro);
		return libroDto;
	}

	@Override
	@Transactional
	public void delete(Long id) throws LibroNotFoundException {
		libroRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Boolean existeLibro(String titulo) {
		Boolean res = false;
		for (LibroDTO libro : findAll()) {
			if (libro.getTitulo().equals(titulo)) {
				res = true;
				break;
			}
		}
		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public List<LibroDTO> findAll(LibroFilterDTO libroFilter) {
		List<LibroDTO> res = new LinkedList<>();
		Specification<Libro> sp = libroFilter.consultar();
		List<Libro> listaLibros = libroRepository.findAll(sp);

		LibroDTO libroDTO;
		for (Libro l : listaLibros) {
			libroDTO = libroDTOConverter.convertToDto(l);
			res.add(libroDTO);
		}

		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<LibroDTO> findAll(LibroFilterDTO libroFilter, Pageable pageable) {
		List<LibroDTO> listLibroDto = new ArrayList<>();
		Page<LibroDTO> res;
		Specification<Libro> sp = libroFilter.consultar();
		Page<Libro> listaLibros = libroRepository.findAll(sp, pageable);

		LibroDTO libroDTO;
		for (Libro l : listaLibros) {
			libroDTO = libroDTOConverter.convertToDto(l);
			listLibroDto.add(libroDTO);
		}

		res = new PageImpl<>(listLibroDto, pageable, listaLibros.getTotalElements());

		return res;
	}

}
