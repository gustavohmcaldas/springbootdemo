package com.gustavohmcaldas.studentapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gustavohmcaldas.studentapi.domain.Student;
import com.gustavohmcaldas.studentapi.requests.StudentPostRequestBody;
import com.gustavohmcaldas.studentapi.requests.StudentPutRequestBody;
import com.gustavohmcaldas.studentapi.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
	
	private final StudentService studentService;
	
	@GetMapping
	public ResponseEntity<Page<Student>> getStudents(Pageable pageable) {
		return ResponseEntity.ok(studentService.getStudents(pageable));
	}
	
	@GetMapping(path = "/{studentId}")
	public ResponseEntity<Student> findById(@PathVariable("studentId") long id) {
		return ResponseEntity.ok(studentService.findById(id));
	}
	
	@GetMapping(path = "/find")
	public ResponseEntity<List<Student>> findByFirstName(@RequestParam String firstName) {
		return ResponseEntity.ok(studentService.findByFirstName(firstName));
	}
	
	@PostMapping
	public ResponseEntity<Student> addStudent(@RequestBody  @Valid StudentPostRequestBody studentPostRequestBody) {
		return new ResponseEntity<>(studentService.addStudent(studentPostRequestBody), HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/admin/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") long id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<Void> updateStudent(@RequestBody @Valid StudentPutRequestBody studentPutRequestBody) {
		studentService.updateStudent(studentPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
