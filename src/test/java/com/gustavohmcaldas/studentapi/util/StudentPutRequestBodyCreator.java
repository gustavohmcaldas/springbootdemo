package com.gustavohmcaldas.studentapi.util;

import com.gustavohmcaldas.studentapi.requests.StudentPutRequestBody;

public class StudentPutRequestBodyCreator {
	public static StudentPutRequestBody createStudentPutRequestBody() {
		return StudentPutRequestBody.builder()
				.id(StudentCreator.createValidStudent().getId())
				.firstName(StudentCreator.createValidStudent().getFirstName())
				.lastName(StudentCreator.createValidStudent().getLastName())
				.email(StudentCreator.createValidStudent().getEmail())
				.age(StudentCreator.createValidStudent().getAge())
				.build();
	}
}
