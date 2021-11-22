package com.zkteco.student.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.student.dto.ResultDTO;
import com.zkteco.student.dto.StudentDTO;
import com.zkteco.student.entity.Student;
import com.zkteco.student.repository.StudentRepository;
import com.zkteco.student.service.StudentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@EnableAutoConfiguration
@Api(value = "student", description = "student Application")
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

	@Autowired
	StudentService service;

	@Autowired
	StudentRepository studentRepository;

	@PostMapping
	public void saveBook(@Valid @RequestBody StudentDTO dto) {
		service.saveBook(dto);
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Fetch Student by Id")
	public ResultDTO fetchById(@PathVariable(value = "id") Integer id) {
		return service.fetchById(id);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Update Student by Id")
	public ResultDTO updateById(@PathVariable(value = "id") Integer id, @Valid @RequestBody StudentDTO dto) {
		ResultDTO result = service.updateById(id, dto);
		return result;
	}

	@DeleteMapping
	@ApiOperation(value = "Delete Student by Id")
	public ResultDTO deleteById(@RequestParam Integer id) {
		return service.deleteById(id);
	}

	@GetMapping
	@ApiOperation(value = "Get Student by Pagable ")
	public Page<Student> getAllStudentPagable(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return service.findPaginate(page, size);

	}

	@GetMapping("/firstname")
	@ApiOperation(value = "Get Student by firstName")
	public Page<Student> getByFirstName(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam String firstname) {
		return service.findByFirstName(firstname, page, size);

	}

	@GetMapping("/lastname")
	@ApiOperation(value = "Get Student by lastName")
	public Page<Student> getByLatsName(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam String lastname) {
		return service.findByLastName(lastname, page, size);
	}

	@GetMapping("/id")
	@ApiOperation(value = "Get Student By Id")
	public Page<Student> getById(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam Integer id) {
		return service.findById(id, page, size);
	}

	@GetMapping("/course")
	@ApiOperation(value = "Get Student By Course")
	public Page<Student> getByCourse(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam String course) {
		return service.findByCourse(course, page, size);
	}

	@PutMapping("/update")
	@ApiOperation(value = "update student ")
	public void updateStudentById(@RequestParam @Valid @RequestBody String firstname, @RequestParam int id) {
		service.updateById(firstname, id);

	}

	@DeleteMapping("/delete")
	@ApiOperation(value = "Delete By Id")
	public void deleteStudentById(@RequestParam Integer id) {
		service.deleteStudentById(id);
	}

	@PostMapping("/insert")
	@ApiOperation(value = "post student data")
	public void insertStudentdata(@RequestParam Integer id, @RequestParam String firstname,
			@RequestParam String lastname, @RequestParam String course) {
		service.insertStudentDetail(id, firstname, lastname, course);
	}
}
