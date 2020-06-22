package com.carlosfunalleras.rest.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.carlosfunalleras.rest.models.entity.Libro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibroFilterDTO {
	
	private String titulo;

	public Specification<Libro> consultar() {

		return new Specification<Libro>() {

			private static final long serialVersionUID = -1684911068640795717L;

			@Override
			public Predicate toPredicate(Root<Libro> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate p = criteriaBuilder.and();

				if (getTitulo() != null && !(getTitulo().isEmpty())) {
					p = criteriaBuilder.and(p, criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")),
							"%" + getTitulo().toLowerCase() + "%"));
				}

				return p;
			}
		};
	}

}
