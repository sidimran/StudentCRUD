package com.zkteco.student.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zkteco.student.dto.ResultDTO;
import com.zkteco.student.dto.StudentDTO;
import com.zkteco.student.entity.Student;

public interface StudentService {

	public void saveBook(StudentDTO studentDto);

	public ResultDTO fetchById(Integer id);

	public ResultDTO deleteById(Integer id);

	public ResultDTO updateById(Integer id, StudentDTO studentDTO);

	public Page<Student> findPaginate(int page, int size);

	Page<Student> findByFirstName(String firstname, int page, int size);

	Page<Student> findByLastName(String lastname, int page, int size);

	Page<Student> findById(Integer id, int page, int size);

	Page<Student> findByCourse(String course, int page, int size);

	void updateById(String firstname, int id);

	void deleteStudentById(Integer id);

	void insertStudentDetail(Integer id,String firstname, String lastname, String course);

}
