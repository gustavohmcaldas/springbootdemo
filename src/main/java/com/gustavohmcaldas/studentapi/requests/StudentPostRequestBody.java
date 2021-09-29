package com.gustavohmcaldas.studentapi.requests;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentPostRequestBody {

	@Schema(description = "This is the student's first name", example = "John", required = true)
	@NotEmpty(message = "First name must not be empty.")
	private String firstName;
	
	@Schema(description = "This is the student's last name", example = "Smith", required = true)
	@NotEmpty(message = "Last name must not be empty.")
	private String lastName;
	
	@Schema(description = "This is the student's email", example = "john@test.com", required = true)
	@NotEmpty(message = "Email must not be empty.")
	private String email;
	
	@Schema(description = "This is the student's age", example = "25")
	private Integer age;
}
