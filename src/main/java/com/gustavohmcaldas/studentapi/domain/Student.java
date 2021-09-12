package com.gustavohmcaldas.studentapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Student")
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", 
			updatable = false)
	private Long id;

	@Column(name = "first_name", 
			nullable = false)
	private String firstName;

	@Column(name = "last_name", 
			nullable = false)
	private String lastName;

	@Column(name = "email", 
			nullable = false)
	private String email;

	@Column(name = "age", 
			nullable = false)
	private Integer age;

	public Student(String firstName, String lastName, String email, Integer age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}
	
}
