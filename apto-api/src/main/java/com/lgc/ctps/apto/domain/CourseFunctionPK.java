package com.lgc.ctps.apto.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The primary key class for the CURSO_FUNCAO database table.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CourseFunctionPK implements Serializable {
	private static final long serialVersionUID = -2208762016341561156L;

	// bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name = "CUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_CFC_CUR"))
	private Course course;

	// bi-directional many-to-one association to Funcao
	@ManyToOne
	@JoinColumn(name = "FNC_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_CFC_FNC"))
	private Function function;
}