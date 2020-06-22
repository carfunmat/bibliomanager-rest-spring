package com.carlosfunalleras.rest.models.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prestamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo implements Serializable {

	private static final long serialVersionUID = 4433187776304100832L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "libro_id")
	@JsonBackReference
	private Libro libro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	@JsonBackReference
	private Usuario usuario;
	
	@Column(name = "fecha_inicio")
	private LocalDate fechaInicio;

	@Column(name = "fecha_final")
	private LocalDate fechaFinal;
	
	@PrePersist
	public void prePersist() {
		fechaInicio = LocalDate.now();
		fechaFinal = fechaInicio.plusDays(7);
	}

}
