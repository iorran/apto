package com.lgc.ctps.apto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The primary key class for the FUNCAO_APROVADOR database table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class FunctionPersonPK implements Serializable {
	private static final long serialVersionUID = 1413576959652471652L;

	//bi-directional many-to-one association to Funcao
	@ManyToOne
	@JoinColumn(name="FNC_ID", foreignKey = @ForeignKey(name = "FK_FPE_FNC"))
	private Function function;

	@Column(name="PES_MATRICULA", nullable=false, length=100)
	private String personId;
}