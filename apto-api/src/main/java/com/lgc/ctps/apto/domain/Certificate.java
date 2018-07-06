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
 * The persistent class for the CERTIFICADO_APTO database table.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CERTIFICADO_APTO")
public class Certificate implements Serializable {
	private static final long serialVersionUID = 6839643795012640008L;

	@GenericGenerator(
	        name = "SQ_CER_ID",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "SQ_CER_ID"),
	                @Parameter(name = "initial_value", value = "1"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@GeneratedValue(generator = "SQ_CER_ID")
	@Id
	@Column(name="CER_ID", unique=true, nullable=false)
	private long id;

	@Column(name="CER_CARGA_HORARIA")
	private BigDecimal workload;

	@Column(name="CER_DATA_EMISSAO")
	private LocalDateTime emissionDate;

	@Column(name="CER_DATA_VALIDADE")
	private LocalDateTime expirationDate;

	@Column(name="CER_DESCRICAO", length=4000)
	private String description;

	@Column(name="PES_MATRICULA", length=100)
	private String userRegistry;

	//bi-directional many-to-one association to CertificadoAprovacao
	@JsonIgnore
	@OneToMany(mappedBy="certificate")
	private List<ApprovalCertificate> approvalCertificates;

	//bi-directional many-to-one association to AnexoApto
	@ManyToOne
	@JoinColumn(name="ANX_ID", foreignKey = @ForeignKey(name = "FK_CER_ANX"))
	private Attachment attachment;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="CUR_ID", foreignKey = @ForeignKey(name = "FK_CER_CUR"))
	private Course course;

	public ApprovalCertificate addCertificadoAprovacao(ApprovalCertificate approvalCertificate) {
		getApprovalCertificates().add(approvalCertificate);
		approvalCertificate.setCertificate(this);
		return approvalCertificate;
	}

	public ApprovalCertificate removeCertificadoAprovacao(ApprovalCertificate approvalCertificate) {
		getApprovalCertificates().remove(approvalCertificate);
		approvalCertificate.setCertificate(null);
		return approvalCertificate;
	}
}