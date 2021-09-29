package com.gustavohmcaldas.studentapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
	
	private final StudentService studentService;
	
	@GetMapping
	@Operation(summary = "List all students (paged)", 
				description = "The default size is 20, use the parameter size to change the default value",
				tags = {"student"})
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
	public ResponseEntity<Page<Student>> getStudents(@ParameterObject Pageable pageable) {
		return ResponseEntity.ok(studentService.getStudents(pageable));
	}
	
	@Operation(summary = "Get student by id", tags = {"student"})
	@GetMapping(path = "/{studentId}")
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
	public ResponseEntity<Student> findById(@PathVariable("studentId") long id) {
		return ResponseEntity.ok(studentService.findById(id));
	}
	
	@GetMapping(path = "/find")
	@Operation(summary = "Get student by first name", tags = {"student"})
	@ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
	public ResponseEntity<List<Student>> findByFirstName(@RequestParam String firstName) {
		return ResponseEntity.ok(studentService.findByFirstName(firstName));
	}
	
	@PostMapping
	@Operation(summary = "Insert a new student", tags = {"student"})
	@ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful Operation"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
	public ResponseEntity<Student> addStudent(@RequestBody  @Valid StudentPostRequestBody studentPostRequestBody) {
		return new ResponseEntity<>(studentService.addStudent(studentPostRequestBody), HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/admin/{studentId}")
	@Operation(summary = "Remove student", 
				description = "Restricted method with admin user only",
				tags = {"student"})
	@ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
	public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") long id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	@Operation(summary = "Update student", 
				tags = {"student"})
	@ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Operation"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
	public ResponseEntity<Void> updateStudent(@RequestBody @Valid StudentPutRequestBody studentPutRequestBody) {
		studentService.updateStudent(studentPutRequestBody);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
