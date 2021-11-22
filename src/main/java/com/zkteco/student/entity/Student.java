package com.zkteco.student.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "studentid")
	private Integer id;
	
	@Column(name = "student_firstName")
	private String firstname;
	
	@Column(name= "student_lastName")
	private String lastname;
	
	@Column(name = "course")
	private String course;

}
