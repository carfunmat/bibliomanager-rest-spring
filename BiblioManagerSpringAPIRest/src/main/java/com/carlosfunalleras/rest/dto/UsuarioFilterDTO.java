package com.carlosfunalleras.rest.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.carlosfunalleras.rest.models.entity.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFilterDTO {

	private String username;

	public Specification<Usuario> consultar() {

		return new Specification<Usuario>() {

			private static final long serialVersionUID = -1385842014515232200L;

			@Override
			public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate p = criteriaBuilder.and();

				if (getUsername() != null && !(getUsername().isEmpty())) {
					p = criteriaBuilder.and(p, criteriaBuilder.like(criteriaBuilder.lower(root.get("username")),
							"%" + getUsername().toLowerCase() + "%"));
				}

				return p;
			}
		};
	}

}
