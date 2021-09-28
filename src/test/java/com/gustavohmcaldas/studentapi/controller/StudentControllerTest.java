package com.gustavohmcaldas.studentapi.controller;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gustavohmcaldas.studentapi.domain.Student;
import com.gustavohmcaldas.studentapi.requests.StudentPostRequestBody;
import com.gustavohmcaldas.studentapi.requests.StudentPutRequestBody;
import com.gustavohmcaldas.studentapi.service.StudentService;
import com.gustavohmcaldas.studentapi.util.StudentCreator;
import com.gustavohmcaldas.studentapi.util.StudentPostRequestBodyCreator;
import com.gustavohmcaldas.studentapi.util.StudentPutRequestBodyCreator;

@ExtendWith(SpringExtension.class)
class StudentControllerTest {

	@InjectMocks
	private StudentController studentController;

	@Mock
	private StudentService studentServiceMock;

	@BeforeEach
	void setup() {
		PageImpl<Student> studentPage = new PageImpl<>(List.of(StudentCreator.createValidStudent()));
		
		BDDMockito.when(studentServiceMock.getStudents(ArgumentMatchers.any()))
				.thenReturn(studentPage);

		BDDMockito.when(studentServiceMock.findById(ArgumentMatchers.anyLong()))
				.thenReturn(StudentCreator.createValidStudent());
		
		BDDMockito.when(studentServiceMock.findByFirstName(ArgumentMatchers.anyString()))
				.thenReturn(List.of(StudentCreator.createValidStudent()));
		
		BDDMockito.when(studentServiceMock.addStudent(ArgumentMatchers.any(StudentPostRequestBody.class)))
				.thenReturn(StudentCreator.createValidStudent());
		
		BDDMockito.doNothing().when(studentServiceMock).updateStudent(ArgumentMatchers.any(StudentPutRequestBody.class));
		
		BDDMockito.doNothing().when(studentServiceMock).deleteStudent(ArgumentMatchers.anyLong());
	}

	@Test
	@DisplayName("getStudents returns list of student inside page object when successful")
	void getStudents_ReturnsListOfStudentsInsidePageObject_WhenSuccessful() {
		String expectedFirstName = StudentCreator.createValidStudent().getFirstName();

		Page<Student> studentPage = studentController.getStudents(null).getBody();

		Assertions.assertThat(studentPage).isNotNull();

		Assertions.assertThat(studentPage.toList()).isNotEmpty().hasSize(1);

		Assertions.assertThat(studentPage.toList().get(0).getFirstName()).isEqualTo(expectedFirstName);
	}

	@Test
	@DisplayName("findById returns student when successful")
	void findById_ReturnsStudent_WhenSuccessful() {
		Long expectedId = StudentCreator.createValidStudent().getId();

		Student student = studentController.findById(1).getBody();

		Assertions.assertThat(student).isNotNull();

		Assertions.assertThat(student.getId()).isEqualTo(expectedId);
	}

	@Test
	@DisplayName("findByFirstName returns a list of student when successful")
	void findByFirstName_ReturnsListOfStudent_WhenSuccessful() {
		String expectedFirstName = StudentCreator.createValidStudent().getFirstName();

		List<Student> students = studentController.findByFirstName("Mike").getBody();

		Assertions.assertThat(students)
				.isNotNull()
				.isNotEmpty()
				.hasSize(1);

		Assertions.assertThat(students.get(0).getFirstName()).isEqualTo(expectedFirstName);
	}
	
	@Test
	@DisplayName("findByFirstName returns an empty list of student when student is not found")
	void findByFirstName_ReturnsEmptyListOfStudent_WhenStudentIsNotFound() {
		BDDMockito.when(studentServiceMock.findByFirstName(ArgumentMatchers.anyString()))
				.thenReturn(Collections.emptyList());

		List<Student> students = studentController.findByFirstName("Mike").getBody();

		Assertions.assertThat(students)
				.isNotNull()
				.isEmpty();
	}
	
	@Test
	@DisplayName("addStudent returns student when succesful")
	void addStudent_ReturnsStudent_WhenSuccessful() {
		Student student = studentController.addStudent(StudentPostRequestBodyCreator.createStudentPostRequestBody()).getBody();

		Assertions.assertThat(student).isNotNull().isEqualTo(StudentCreator.createValidStudent());
	}
	
	@Test
	@DisplayName("updateStudent updates student when succesful")
	void updateStudent_UpdatesStudent_WhenSuccessful() {
		
		Assertions.assertThatCode(() -> studentController.updateStudent(StudentPutRequestBodyCreator.createStudentPutRequestBody()))
				.doesNotThrowAnyException();
		
		ResponseEntity<Void> entity = studentController.updateStudent(StudentPutRequestBodyCreator.createStudentPutRequestBody());
		
		Assertions.assertThat(entity).isNotNull();
		
		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
	
	@Test
	@DisplayName("deleteStudent removes student when succesful")
	void deleteStudent_RemovesStudent_WhenSuccessful() {
		
		Assertions.assertThatCode(() -> studentController.deleteStudent(1))
				.doesNotThrowAnyException();
		
		ResponseEntity<Void> entity = studentController.deleteStudent(1);
		
		Assertions.assertThat(entity).isNotNull();
		
		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
}
