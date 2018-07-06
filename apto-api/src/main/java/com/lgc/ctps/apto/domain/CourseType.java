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
 * The persistent class for the TIPO_CURSO database table.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TIPO_CURSO")
public class CourseType implements Serializable {
	private static final long serialVersionUID = 5121244246788432174L;

	@GenericGenerator(
	        name = "SQ_TCU_ID",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "SQ_TCU_ID"),
	                @Parameter(name = "initial_value", value = "1"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@GeneratedValue(generator = "SQ_TCU_ID")
	@Id
	@Column(name="TCU_ID", unique=true, nullable=false)
	private long id;

	@Column(name="TCU_DESCRICAO", length=4000)
	private String description;

	//bi-directional many-to-one association to Curso
	@JsonIgnore
	@OneToMany(mappedBy="courseType")
	private List<Course> courses;

	public Course addCurso(Course course) {
		getCourses().add(course);
		course.setCourseType(this);
		return course;
	}
	public Course removeCurso(Course course) {
		getCourses().remove(course);
		course.setCourseType(null);
		return course;
	}
}