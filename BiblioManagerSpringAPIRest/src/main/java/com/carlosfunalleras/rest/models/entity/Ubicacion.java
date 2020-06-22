package com.carlosfunalleras.rest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "ubicaciones")
@Data
// @NoArgsConstructor
@AllArgsConstructor
public class Ubicacion implements Serializable {

	private static final long serialVersionUID = -6022398494651809615L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer pasillo;
	private Integer fila;
	private String seccion;
	
	@OneToMany(mappedBy = "ubicacion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Libro> libros;
	
	public Ubicacion() {
		libros = new ArrayList<>();
	}

}
