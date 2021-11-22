package com.zkteco.student.service.impl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.zkteco.student.converter.StudentConverter;
import com.zkteco.student.dto.ResultDTO;
import com.zkteco.student.dto.StudentDTO;
import com.zkteco.student.entity.Student;
import com.zkteco.student.repository.StudentRepository;
import com.zkteco.student.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	StudentConverter studentConverter;

	@Autowired
	StudentService studentService;

	@Override
	public void saveBook(StudentDTO dto) {

		try {

			Student student = studentConverter.DtoToEntity(dto);
			student = studentRepository.save(student);
			studentConverter.entityToDto(student);

		} catch (NullPointerException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public ResultDTO fetchById(Integer id) {

		Optional<Student> student = studentRepository.findById(id);
		ResultDTO dto = new ResultDTO();
		if (student.get().getId() != null) {
			StudentDTO studentdto = studentConverter.entityToDto(student.get());
			dto.setData(studentdto);
			return dto;
		} else {
			dto.setMessage("Student Not available");
			System.out.println("---->>>>>>" + dto);
		}
		return dto;
	}

	@Override
	public ResultDTO deleteById(Integer id) {

		Optional<Student> student = studentRepository.findById(id);
		if (student.get().getId() != id) {
			ResultDTO dto = new ResultDTO();
			dto.setMessage("Student Id Not Available");
			return dto;
		} else {
			studentRepository.deleteById(id);
		}
		return null;

	}

	@Override
	public ResultDTO updateById(Integer id, StudentDTO studentDto) {

		Optional<Student> student = studentRepository.findById(id);
		ResultDTO dto = new ResultDTO();

		if (student.get().getId() == null) {
			dto.setMessage("Student Id not found");
			return dto;
		}

		Student student2 = student.get();
		if (Objects.nonNull(studentDto.getFirstname()) && !"".equalsIgnoreCase(studentDto.getFirstname())) {
			student2.setFirstname(studentDto.getFirstname());
		}
		if (Objects.nonNull(studentDto.getLastname()) && !"".equalsIgnoreCase(studentDto.getLastname())) {
			student2.setLastname(studentDto.getLastname());
		}

		if (Objects.nonNull(studentDto.getCourse()) && !"".equalsIgnoreCase(studentDto.getCourse())) {
			student2.setCourse(studentDto.getCourse());
		}

		student2 = studentRepository.save(student2);

		StudentDTO studentDTO = studentConverter.entityToDto(student2);
		dto.setData(studentDTO);

		return dto;
	}

	@Override
	public Page<Student> findPaginate(int page, int size) {

		Pageable page1 = PageRequest.of(page, size);

		Page<Student> page2 = studentRepository.findAll(page1);
		return page2;

	}

	@Override
	public Page<Student> findByFirstName(String firstname, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return studentRepository.findByFirstNameNativeSQL(firstname, pageable);

	}

	@Override
	public Page<Student> findByLastName(String lastname, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return studentRepository.findBylastNameNativeSQL(lastname, pageable);
	}

	@Override
	public Page<Student> findById(Integer id, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return studentRepository.findByIdNativeSQL(id, pageable);

	}

	@Override
	public Page<Student> findByCourse(String course, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return studentRepository.findByCoursenativeSQL(course, pageable);

	}

	@Override
	public void updateById(String firstname, int id) {
		studentRepository.updateStudentById(firstname, id);

	}

	@Override
	public void deleteStudentById(Integer id) {
		studentRepository.deleteStudentByIds(id);
	}

	@Override
	public void insertStudentDetail(Integer id,String firstname, String lastname, String course) {

		studentRepository.insertStudentData(id,firstname, lastname, course);

	}

}
