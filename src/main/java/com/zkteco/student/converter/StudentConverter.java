package com.zkteco.student.converter;

import org.springframework.stereotype.Component;

import com.zkteco.student.dto.StudentDTO;
import com.zkteco.student.entity.Student;

@Component
public class StudentConverter {

	public StudentDTO entityToDto(Student student) {

		StudentDTO studentDto = new StudentDTO();
		studentDto.setId(student.getId());
		studentDto.setFirstname(student.getFirstname());
		studentDto.setLastname(student.getLastname());
		studentDto.setCourse(student.getCourse());

		return studentDto;

	}

	public Student DtoToEntity(StudentDTO dto) {

		Student student = new Student();
		student.setId(dto.getId());
		student.setFirstname(dto.getFirstname());
		student.setLastname(dto.getLastname());
		student.setCourse(dto.getCourse());

		return student;
	}

}
