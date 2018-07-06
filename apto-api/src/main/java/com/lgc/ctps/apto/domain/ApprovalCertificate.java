package com.lgc.ctps.apto.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the CERTIFICADO_APROVACAO database table.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CERTIFICADO_APROVACAO")
public class ApprovalCertificate implements Serializable {
	private static final long serialVersionUID = -2931786708688887360L;

	@GenericGenerator(
	        name = "SQ_CAP_ID",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "SQ_CAP_ID"),
	                @Parameter(name = "initial_value", value = "1"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@GeneratedValue(generator = "SQ_CAP_ID")
	@Id
	@Column(name="CAP_ID", unique=true, nullable=false)
	private Long id;

	@Column(name="CAP_MATRICULA", length=100, unique=true, nullable=false)
	private String personId;

	@Column(name="CAP_DATA_APROVACAO")
	private LocalDateTime approvalDate;

	@Column(name="CAP_OBSERVACAO", length=4000)
	private String note;

	@Column(name="CAP_STATUS")
	private Boolean status;

	//bi-directional many-to-one association to CertificadoApto
	@ManyToOne
	@JoinColumn(name="CER_ID", nullable=false, foreignKey = @ForeignKey(name = "FK_CAP_CER"))
	private Certificate certificate;
}