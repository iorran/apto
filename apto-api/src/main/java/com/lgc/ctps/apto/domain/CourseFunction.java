package com.lgc.ctps.apto.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the CURSO_FUNCAO database table.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CURSO_FUNCAO")
public class CourseFunction implements Serializable {
	private static final long serialVersionUID = -6570856232105815357L;

	@Id
	private CourseFunctionPK id;

	@Column(name="CFP_DATA_INI_VALIDADE")
	private LocalDateTime cfpDataIniValidade;
}