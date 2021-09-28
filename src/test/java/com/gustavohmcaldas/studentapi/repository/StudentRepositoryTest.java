package com.gustavohmcaldas.studentapi.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gustavohmcaldas.studentapi.domain.Student;
import com.gustavohmcaldas.studentapi.util.StudentCreator;

@DataJpaTest
@DisplayName("Tests for Student Repository")
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	@DisplayName("Save persists student when successful")
	void save_PersistStudent_WhenSuccessful() {
		Student studentToBeSaved = StudentCreator.createStudentToBeSaved();
		Student savedStudent = this.studentRepository.save(studentToBeSaved);
		Assertions.assertThat(savedStudent).isNotNull();
		
		Assertions.assertThat(savedStudent.getId()).isNotNull();
		
		Assertions.assertThat(savedStudent.getFirstName()).isEqualTo(studentToBeSaved.getFirstName());
	}
	
	@Test
	@DisplayName("Update student when successful")
	void save_UpdatesStudent_WhenSuccessful() {
		Student studentToBeSaved = StudentCreator.createStudentToBeSaved();
		
		Student savedStudent = this.studentRepository.save(studentToBeSaved);
		savedStudent.setFirstName("Bob");
		
		Student updatedStudent = this.studentRepository.save(savedStudent);
		
		Assertions.assertThat(updatedStudent).isNotNull();
		
		Assertions.assertThat(updatedStudent.getId()).isNotNull();
		
		Assertions.assertThat(updatedStudent.getFirstName()).isEqualTo(savedStudent.getFirstName());
	}
	
	@Test
	@DisplayName("Delete student when successful")
	void delete_RemovesStudent_WhenSuccessful() {
		Student studentToBeSaved = StudentCreator.createStudentToBeSaved();
		
		Student savedStudent = this.studentRepository.save(studentToBeSaved);
		
		this.studentRepository.delete(savedStudent);
		
		Optional<Student> optionalStudent = this.studentRepository.findById(savedStudent.getId());
		
		Assertions.assertThat(optionalStudent).isEmpty();
	}
	
	@Test
	@DisplayName("Find by FirstName returns list of student when successful")
	void findByFirstName_ReturnsListOfStudent_WhenSuccessful() {
		Student studentToBeSaved = StudentCreator.createStudentToBeSaved();
		
		Student savedStudent = this.studentRepository.save(studentToBeSaved);
		
		String firstName = savedStudent.getFirstName();
		
		List<Student> students = this.studentRepository.findByFirstName(firstName);
		
		Assertions.assertThat(students)
				.isNotEmpty()
				.contains(savedStudent);
	}
	
	@Test
	@DisplayName("Find by FirstName returns empty list no student is found")
	void findByFirstName_ReturnsEmptyyList_WhenStudentIsNotFound() {
		List<Student> students = this.studentRepository.findByFirstName("Jack");
		
		Assertions.assertThat(students).isEmpty();
	}
	
	@Test
	@DisplayName("Save throw ConstraintViolationException when firstName is empty")
	void save_ThrowsConstraintViolationException_WhenNameIsEmpty() {
		Student student = new Student();
		Assertions.assertThatThrownBy(() -> this.studentRepository.save(student))
				.isInstanceOf(ConstraintViolationException.class);
	}
}
