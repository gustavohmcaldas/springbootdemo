package com.gustavohmcaldas.studentapi.requests;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentPutRequestBody {
	
	@Schema(description = "This is the student's id", example = "1")
	private Long id;
	
	@Schema(description = "This is the student's first name", example = "Mary", required = true)
	@NotEmpty(message = "First name must not be empty.")
	private String firstName;
	
	@Schema(description = "This is the student's last name", example = "Smith", required = true)
	@NotEmpty(message = "Last name must not be empty.")
	private String lastName;
	
	@Schema(description = "This is the student's email", example = "mary@test.com", required = true)
	@NotEmpty(message = "Email must not be empty.")
	private String email;
	
	@Schema(description = "This is the student's age", example = "33")
	private Integer age;
}
