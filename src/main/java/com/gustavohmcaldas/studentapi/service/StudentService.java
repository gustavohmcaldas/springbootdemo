package com.gustavohmcaldas.studentapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gustavohmcaldas.studentapi.domain.Student;
import com.gustavohmcaldas.studentapi.exception.BadRequestException;
import com.gustavohmcaldas.studentapi.mapper.StudentMapper;
import com.gustavohmcaldas.studentapi.repository.StudentRepository;
import com.gustavohmcaldas.studentapi.requests.StudentPostRequestBody;
import com.gustavohmcaldas.studentapi.requests.StudentPutRequestBody;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	
	private final StudentRepository studentRepository;

	public Page<Student> getStudents(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}
	
	public Student findById(long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("Student not found."));
	}
	
	public List<Student> findByFirstName(String firstName) {
		return studentRepository.findByFirstName(firstName);
	}

	public Student addStudent(StudentPostRequestBody studentPostRequestBody) {
		return studentRepository.save(StudentMapper.INSTANCE.toStudent(studentPostRequestBody));
	}

	public void deleteStudent(Long studentId) {
		studentRepository.delete(findById(studentId));
	}

	public void updateStudent(StudentPutRequestBody studentPutRequestBody) {
		Student savedStudent = findById(studentPutRequestBody.getId());
		Student student = StudentMapper.INSTANCE.toStudent(studentPutRequestBody);
		student.setId(savedStudent.getId()); 
		studentRepository.save(student);
	}
}
