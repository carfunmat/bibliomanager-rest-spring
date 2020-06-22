package com.carlosfunalleras.rest.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.carlosfunalleras.rest.models.entity.Autor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutorFilterDTO {
	
	private String nombre;

	public Specification<Autor> consultar() {

		return new Specification<Autor>() {

			private static final long serialVersionUID = 7075478781992571845L;

			@Override
			public Predicate toPredicate(Root<Autor> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate p = criteriaBuilder.and();

				if (getNombre() != null && !(getNombre().isEmpty())) {
					p = criteriaBuilder.and(p, criteriaBuilder.like(criteriaBuilder.lower(root.get("nombre")),
							"%" + getNombre().toLowerCase() + "%"));
				}

				return p;
			}
		};
	}

}
