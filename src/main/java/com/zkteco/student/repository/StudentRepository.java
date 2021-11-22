package com.zkteco.student.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.zkteco.student.dto.ResultDTO;
import com.zkteco.student.entity.Student;

@Repository
public interface StudentRepository
		extends JpaRepository<Student, Integer>, PagingAndSortingRepository<Student, Integer> {

	@Query(value = "SELECT * FROM student p WHERE p.student_first_name = :firstname", nativeQuery = true)
	Page<Student> findByFirstNameNativeSQL(@Param("firstname") String firstName, Pageable pageable);

	@Query(value = "SELECT * FROM student p WHERE p.student_last_name = :lastname", nativeQuery = true)
	Page<Student> findBylastNameNativeSQL(@Param("lastname") String lastName, Pageable pageable);

	@Query(value = "select * FROM student p where p.studentid = :id", nativeQuery = true)
	Page<Student> findByIdNativeSQL(@Param("id") Integer id, Pageable pageable);

	@Query(value = "select * from student p where p.course = :course", nativeQuery = true)
	Page<Student> findByCoursenativeSQL(@Param("course") String course, Pageable pageable);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update Student SET student_first_name = :firstname where studentid = :id", nativeQuery = true)
	public void updateStudentById(@Param("firstname") String firstname, @Param("id") int id);

	@Modifying
	@Query(value = "delete from student p where p.studentid = :id", nativeQuery = true)
	void deleteStudentByIds(@Param("id") Integer id);

	@Modifying(clearAutomatically = true)
	@Query(value = "insert into student  (studentid,student_first_name,student_last_name,course) VALUES(:studentid,:firstname,:lastname,:course)", nativeQuery = true)
	@Transactional
	void insertStudentData(@Param("studentid") Integer id, @Param("firstname") String firstname,
			@Param("lastname") String lastname, @Param("course") String course);

}
