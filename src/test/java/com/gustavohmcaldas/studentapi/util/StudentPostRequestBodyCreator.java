package com.gustavohmcaldas.studentapi.util;

import com.gustavohmcaldas.studentapi.requests.StudentPostRequestBody;

public class StudentPostRequestBodyCreator {

	public static StudentPostRequestBody createStudentPostRequestBody() {
		return StudentPostRequestBody.builder()
				.firstName(StudentCreator.createStudentToBeSaved().getFirstName())
				.lastName(StudentCreator.createStudentToBeSaved().getLastName())
				.email(StudentCreator.createStudentToBeSaved().getEmail())
				.age(StudentCreator.createStudentToBeSaved().getAge())
				.build();
	}
}
