package com.lgc.ctps.apto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the CLASSIFICACAO_CURSO database table.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CLASSIFICACAO_CURSO")
public class CourseRating implements Serializable {
	private static final long serialVersionUID = 6483557338573259024L;

	@GenericGenerator(
	        name = "SQ_CLC_ID",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "SQ_CLC_ID"),
	                @Parameter(name = "initial_value", value = "1"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@GeneratedValue(generator = "SQ_CLC_ID")
	@Id
	@Column(name="CLC_ID", unique=true, nullable=false)
	private long id;

	@Column(name="CLC_DESCRICAO", length=2000)
	private String description;

	@Column(name="CLC_SIGLA", length=10)
	private String initials;
}
