package com.lgc.ctps.apto.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * The persistent class for the CURSO database table.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CURSO")
public class Course implements Serializable {
	private static final long serialVersionUID = 1708875760442294206L;

	@GenericGenerator(
	        name = "SQ_CUR_ID",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "SQ_CUR_ID"),
	                @Parameter(name = "initial_value", value = "1"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@GeneratedValue(generator = "SQ_CUR_ID")
	@Id
	@Column(name="CUR_ID", unique=true, nullable=false)
	private Long id;

	@Column(name="CUR_CARGA_HORARIA")
	private BigDecimal workload;

	@Column(name="CUR_DATA_CADASTRO")
	private LocalDateTime registrationDate;

	@Column(name="CUR_DESCRICAO", length=4000)
	private String description;

	@Column(name="CUR_DESCRICAO_EXIGENCIA", length=4000)
	private String requirement;

	@Column(name="CUR_EXIGE_ANEXO", nullable=false)
	private boolean attachmentRequired;

	@Column(name="CUR_EXIGE_VALIDADE", nullable=false)
	private boolean dateExpirationRequired;

	@Column(name="CUR_NOME", nullable=false, length=150)
	private String name;

	//bi-directional many-to-one association to CertificadoApto
	@JsonIgnore
	@OneToMany(mappedBy="course")
	private List<Certificate> certificates;

	//bi-directional many-to-one association to ItemListaApto
	@ManyToOne
	@JoinColumn(name="CLC_ID_NVL1", foreignKey = @ForeignKey(name = "FK_CUR_CLC1"))
	private CourseRating courseRatingOne;

	//bi-directional many-to-one association to ItemListaApto
	@ManyToOne
	@JoinColumn(name="CLC_ID_NVL2", foreignKey = @ForeignKey(name = "FK_CUR_CLC2"))
	private CourseRating courseRatingTwo;

	//bi-directional many-to-one association to TipoCurso
	@ManyToOne
	@JoinColumn(name="TCU_ID", foreignKey = @ForeignKey(name = "FK_CUR_TCU"))
	private CourseType courseType;

	//bi-directional many-to-one association to CursoFuncao
	@JsonIgnore
	@OneToMany(mappedBy="id.course")
	private List<CourseFunction> functions;

	public Certificate addCertificadoApto(Certificate certificate) {
		getCertificates().add(certificate);
		certificate.setCourse(this);
		return certificate;
	}

	public Certificate removeCertificadoApto(Certificate certificate) {
		getCertificates().remove(certificate);
		certificate.setCourse(null);
		return certificate;
	}
}