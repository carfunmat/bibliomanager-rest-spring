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

import com.carlosfunalleras.rest.dto.PrestamoDTO;
import com.carlosfunalleras.rest.dto.PrestamoFilterDTO;
import com.carlosfunalleras.rest.dto.converter.PrestamoDTOConverter;
import com.carlosfunalleras.rest.exceptions.PrestamoNotFoundException;
import com.carlosfunalleras.rest.models.entity.Prestamo;
import com.carlosfunalleras.rest.models.repository.IPrestamoRepository;

@Service
public class PrestamoServiceImpl implements IPrestamoService {

	@Autowired
	private IPrestamoRepository prestamoRepository;
	
	@Autowired
	private PrestamoDTOConverter prestamoDTOConverter;
	
	@Override
	@Transactional(readOnly = true)
	public List<PrestamoDTO> findAll() {
		List<PrestamoDTO> res = new LinkedList<>();
		List<Prestamo> listaPrestamos = prestamoRepository.findAll();

		PrestamoDTO prestamoDTO;
		for (Prestamo p : listaPrestamos) {
			prestamoDTO = prestamoDTOConverter.convertToDto(p);
			res.add(prestamoDTO);
		}

		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public PrestamoDTO findById(Long id) throws PrestamoNotFoundException {
		Prestamo p = prestamoRepository.findById(id).orElseThrow(PrestamoNotFoundException::new);
		return prestamoDTOConverter.convertToDto(p);
	}

	@Override
	@Transactional
	public PrestamoDTO save(PrestamoDTO prestamoDto) {
		// TODO incluir que no se puedan prestar libros ya prestados
		Prestamo prestamo = prestamoDTOConverter.convertToPrestamo(prestamoDto);
		prestamo = prestamoRepository.save(prestamo);
		prestamoDto = prestamoDTOConverter.convertToDto(prestamo);
		return prestamoDto;
	}

	@Override
	@Transactional
	public void delete(Long id) throws PrestamoNotFoundException {
		prestamoRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PrestamoDTO> findAll(PrestamoFilterDTO prestamoFilter) {
		List<PrestamoDTO> res = new LinkedList<>();
		Specification<Prestamo> sp = prestamoFilter.consultar();
		List<Prestamo> listaPrestamos = prestamoRepository.findAll(sp);

		PrestamoDTO prestamoDTO;
		for (Prestamo p : listaPrestamos) {
			prestamoDTO = prestamoDTOConverter.convertToDto(p);
			res.add(prestamoDTO);
		}

		return res;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PrestamoDTO> findAll(PrestamoFilterDTO prestamoFilter, Pageable pageable) {
		List<PrestamoDTO> listPrestamoDto = new ArrayList<>();
		Page<PrestamoDTO> res;
		Specification<Prestamo> sp = prestamoFilter.consultar();
		Page<Prestamo> listaPrestamos = prestamoRepository.findAll(sp, pageable);

		PrestamoDTO prestamoDTO;
		for (Prestamo p : listaPrestamos) {
			prestamoDTO = prestamoDTOConverter.convertToDto(p);
			listPrestamoDto.add(prestamoDTO);
		}

		res = new PageImpl<>(listPrestamoDto, pageable, listaPrestamos.getTotalElements());

		return res;
	}

}
