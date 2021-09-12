package com.gustavohmcaldas.studentapi.requests;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class StudentPostRequestBody {

	@NotEmpty(message = "First name must not be empty.")
	private String firstName;
	
	@NotEmpty(message = "Last name must not be empty.")
	private String lastName;
	
	@NotEmpty(message = "Email must not be empty.")
	private String email;
	
	private Integer age;
}