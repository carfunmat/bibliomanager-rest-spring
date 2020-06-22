package com.carlosfunalleras.rest.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.carlosfunalleras.rest.models.entity.Prestamo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoFilterDTO {
	
	private String username;
	
	public Specification<Prestamo> consultar() {

		return new Specification<Prestamo>() {

			private static final long serialVersionUID = 2138668715168549945L;

			@Override
			public Predicate toPredicate(Root<Prestamo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate p = criteriaBuilder.and();

				if (getUsername() != null && !(getUsername().isEmpty())) {
					p = criteriaBuilder.and(p, criteriaBuilder.like(criteriaBuilder.lower(root.get("usuario").get("username")),
							"%" + getUsername().toLowerCase() + "%"));
				}

				return p;
			}
		};
	}

}
