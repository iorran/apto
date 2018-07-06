package com.lgc.ctps.apto.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the FUNCAO database table.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="FUNCAO")
public class Function implements Serializable {
	private static final long serialVersionUID = 1L;

//	@GenericGenerator(
//	        name = "SQ_FNC_ID",
//	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//	        parameters = {
//	                @Parameter(name = "sequence_name", value = "SQ_FNC_ID"),
//	                @Parameter(name = "initial_value", value = "1"),
//	                @Parameter(name = "increment_size", value = "1")
//	        }
//	)
//	@GeneratedValue(generator = "SQ_FNC_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name="FNC_ID", unique=true, nullable=false)
	private Long id;

	@Column(name="FNC_DESCRICAO", length=4000)
	private String description;

	@Column(name="FNC_NOME", nullable=false, length=100)
	private String name;

	@Column(name="FNC_VAGA_PETROBRAS", nullable=false)
	private boolean petrobrasPost;

	//bi-directional many-to-one association to CursoFuncaoPessoa
	@JsonIgnore
	@OneToMany(mappedBy="id.function")
	private List<CourseFunction> cursos;

	//bi-directional many-to-one association to ItemListaApto
	@ManyToOne
	@JoinColumn(name="CLF_ID_NVL1", foreignKey = @ForeignKey(name = "FK_FNC_CLF1"))
	private FunctionRating functionRatingOne;

	//bi-directional many-to-one association to ItemListaApto
	@ManyToOne
	@JoinColumn(name="CLF_ID_NVL2", foreignKey = @ForeignKey(name = "FK_FNC_CLF2"))
	private FunctionRating functionRatingTwo;

	//bi-directional many-to-one association to TipoFuncao
	@ManyToOne
	@JoinColumn(name="TFC_ID", foreignKey = @ForeignKey(name = "FK_FNC_TFC"))
	private FunctionType functionType;

	//bi-directional many-to-one association to FuncaoAprovador
	@JsonIgnore
	@OneToMany(mappedBy="id.function")
	private List<FunctionApprover> functionApprovers;

	//bi-directional many-to-one association to FuncaoPessoa
	@JsonIgnore
	@OneToMany(mappedBy="id.function")
	private List<FunctionPerson> persons;
}