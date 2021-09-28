package com.gustavohmcaldas.studentapi.util;

import com.gustavohmcaldas.studentapi.domain.Student;

public class StudentCreator {

	public static Student createStudentToBeSaved() {
		return Student.builder()
				.firstName("Michael")
				.lastName("Jordan")
				.email("michael@test.com")
				.age(22)
				.build();
	}
	
	public static Student createValidStudent() {
		return Student.builder()
				.id(1L)
				.firstName("Michael")
				.lastName("Jordan")
				.email("michael@test.com")
				.age(22)
				.build();
	}
	
	public static Student createValidUpdatedStudent() {
		return Student.builder()
				.id(1L)
				.firstName("Bob")
				.lastName("Marley")
				.email("bob@test.com")
				.age(32)
				.build();
	}
}
