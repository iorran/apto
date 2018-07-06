package com.lgc.ctps.apto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the FUNCAO_PESSOA database table.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="FUNCAO_PESSOA")
public class FunctionPerson implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FunctionPersonPK id;

	@Column(name="FNP_PRINCIPAL", nullable=false)
	private boolean primary;
}