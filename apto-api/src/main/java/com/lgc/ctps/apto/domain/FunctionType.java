package com.lgc.ctps.apto.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the TIPO_FUNCAO database table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="TIPO_FUNCAO")
public class FunctionType implements Serializable {
	private static final long serialVersionUID = 4378306801458468909L;

	@GenericGenerator(
	        name = "SQ_TFC_ID",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "SQ_TFC_ID"),
	                @Parameter(name = "initial_value", value = "1"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@GeneratedValue(generator = "SQ_TFC_ID")
	@Id
	@Column(name="TFC_ID", unique=true, nullable=false)
	private Long id;

	@Column(name="TFC_DESCRICAO", length=4000)
	private String description;

	//bi-directional many-to-one association to Funcao
	@JsonIgnore
	@OneToMany(mappedBy="functionType")
	private List<Function> functions;

	public Function addFuncao(Function function) {
		getFunctions().add(function);
		function.setFunctionType(this);
		return function;
	}

	public Function removeFuncao(Function function) {
		getFunctions().remove(function);
		function.setFunctionType(null);
		return function;
	}
}