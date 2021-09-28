package com.gustavohmcaldas.studentapi.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gustavohmcaldas.studentapi.domain.Student;
import com.gustavohmcaldas.studentapi.exception.BadRequestException;
import com.gustavohmcaldas.studentapi.repository.StudentRepository;
import com.gustavohmcaldas.studentapi.util.StudentCreator;
import com.gustavohmcaldas.studentapi.util.StudentPostRequestBodyCreator;
import com.gustavohmcaldas.studentapi.util.StudentPutRequestBodyCreator;

@ExtendWith(SpringExtension.class)
class StudentServiceTest {

	@InjectMocks
	private StudentService studentService;

	@Mock
	private StudentRepository studentRepositoryMock;

	@BeforeEach
	void setup() {
		PageImpl<Student> studentPage = new PageImpl<>(List.of(StudentCreator.createValidStudent()));
		
		BDDMockito.when(studentRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
        .thenReturn(studentPage);

		BDDMockito.when(studentRepositoryMock.findById(ArgumentMatchers.anyLong()))
				.thenReturn(Optional.of(StudentCreator.createValidStudent()));
		
		BDDMockito.when(studentRepositoryMock.findByFirstName(ArgumentMatchers.anyString()))
				.thenReturn(List.of(StudentCreator.createValidStudent()));
		
		BDDMockito.when(studentRepositoryMock.save(ArgumentMatchers.any(Student.class)))
				.thenReturn(StudentCreator.createValidStudent());
		
		BDDMockito.doNothing().when(studentRepositoryMock).delete(ArgumentMatchers.any(Student.class));
	}

	@Test
	@DisplayName("getStudents returns list of student inside page object when successful")
	void getStudents_ReturnsListOfStudentsInsidePageObject_WhenSuccessful() {
		String expectedFirstName = StudentCreator.createValidStudent().getFirstName();

		Page<Student> studentPage = studentService.getStudents(PageRequest.of(1, 1));

		Assertions.assertThat(studentPage).isNotNull();

		Assertions.assertThat(studentPage.toList()).isNotEmpty().hasSize(1);

		Assertions.assertThat(studentPage.toList().get(0).getFirstName()).isEqualTo(expectedFirstName);
	}

	@Test
	@DisplayName("findById returns student when successful")
	void findById_ReturnsStudent_WhenSuccessful() {
		Long expectedId = StudentCreator.createValidStudent().getId();

		Student student = studentService.findById(1);

		Assertions.assertThat(student).isNotNull();

		Assertions.assertThat(student.getId()).isEqualTo(expectedId);
	}
	
    @Test
    @DisplayName("findById throws BadRequestException when anime is not found")
    void findById_ThrowsBadRequestException_WhenAnimeIsNotFound(){
        BDDMockito.when(studentRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> studentService.findById(1));
    }

	@Test
	@DisplayName("findByFirstName returns a list of student when successful")
	void findByFirstName_ReturnsListOfStudent_WhenSuccessful() {
		String expectedFirstName = StudentCreator.createValidStudent().getFirstName();

		List<Student> students = studentService.findByFirstName("Mike");

		Assertions.assertThat(students)
				.isNotNull()
				.isNotEmpty()
				.hasSize(1);

		Assertions.assertThat(students.get(0).getFirstName()).isEqualTo(expectedFirstName);
	}
	
	@Test
	@DisplayName("findByFirstName returns an empty list of student when student is not found")
	void findByFirstName_ReturnsEmptyListOfStudent_WhenStudentIsNotFound() {
		BDDMockito.when(studentRepositoryMock.findByFirstName(ArgumentMatchers.anyString()))
				.thenReturn(Collections.emptyList());

		List<Student> students = studentService.findByFirstName("Mike");

		Assertions.assertThat(students)
				.isNotNull()
				.isEmpty();
	}
	
	@Test
	@DisplayName("addStudent returns student when succesful")
	void addStudent_ReturnsStudent_WhenSuccessful() {
		Student student = studentService.addStudent(StudentPostRequestBodyCreator.createStudentPostRequestBody());

		Assertions.assertThat(student).isNotNull().isEqualTo(StudentCreator.createValidStudent());
	}
	
	@Test
	@DisplayName("updateStudent updates student when succesful")
	void updateStudent_UpdatesStudent_WhenSuccessful() {
		
		Assertions.assertThatCode(() -> studentService.updateStudent(StudentPutRequestBodyCreator.createStudentPutRequestBody()))
				.doesNotThrowAnyException();
		
	}
	
	@Test
	@DisplayName("deleteStudent removes student when succesful")
	void deleteStudent_RemovesStudent_WhenSuccessful() {
		
		Assertions.assertThatCode(() -> studentService.deleteStudent(1L))
				.doesNotThrowAnyException();
	}
}
