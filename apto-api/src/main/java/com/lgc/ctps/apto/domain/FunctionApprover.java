package com.lgc.ctps.apto.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the FUNCAO_APROVADOR database table.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="FUNCAO_APROVADOR")
public class FunctionApprover implements Serializable {
	private static final long serialVersionUID = -7934281551973116382L;

	@EmbeddedId
	private FunctionApproverPK id;
}