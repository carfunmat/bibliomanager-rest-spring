package com.carlosfunalleras.rest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "libros")
@Data
// @NoArgsConstructor
@AllArgsConstructor
public class Libro implements Serializable {

	private static final long serialVersionUID = -3211506286518177943L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String titulo;

	@Column(name = "numero_paginas")
	private Integer numeroPaginas;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autor_id")
	@JsonBackReference
	private Autor autor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ubicacion_id")
	@JsonBackReference
	private Ubicacion ubicacion;

	@OneToMany(mappedBy = "libro", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Prestamo> prestamos;
	
	public Libro() {
		prestamos = new ArrayList<>();
	}

}
