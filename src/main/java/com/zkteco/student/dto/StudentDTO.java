package com.zkteco.student.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class StudentDTO {

	private Integer id;

	@NotNull(message = "Enter first Name")
	@Column(name = "student_firstName")
	private String firstname;

	@NotNull(message = "Enter last Name")
	private String lastname;

	@NotNull(message = "Enter course detail")
	private String course;

}
