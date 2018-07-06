package com.lgc.ctps.apto.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
 * The persistent class for the ANEXO_APTO database table.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ANEXO_APTO")
public class Attachment implements Serializable {
	private static final long serialVersionUID = -782228110032781995L;

	@GenericGenerator(
	        name = "SQ_ANX_ID",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "SQ_ANX_ID"),
	                @Parameter(name = "initial_value", value = "1"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@GeneratedValue(generator = "SQ_ANX_ID")
	@Id
	@Column(name="ANX_ID", unique=true, nullable=false)
	private Long id;

	@Lob
	@Column(name="ANX_ARQUIVO")
	private byte[] file;

	@Column(name="ANX_ATIVO")
	private Boolean status;

	@Column(name="ANX_CHAVE_UPLOAD", length=4)
	private String userKey;

	@Column(name="ANX_DATA_UPLOAD")
	private LocalDateTime uploadDate;

	@Column(name="ANX_NOME_ARQUIVO", length=1000)
	private String fileName;

	@Column(name="ANX_TAMANHO_ARQUIVO")
	private BigDecimal fileSize;

	@Column(name="ANX_TIPO_ARQUIVO", length=100)
	private String fileType;

	//bi-directional many-to-one association to CertificadoApto
	@JsonIgnore
	@OneToMany(mappedBy="attachment")
	private List<Certificate> certificates;
}