package com.gustavohmcaldas.studentapi.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gustavohmcaldas.studentapi.domain.Student;
import com.gustavohmcaldas.studentapi.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class StudentConfig {
	
	private final StudentRepository studentRepository;
	
	@Bean
	CommandLineRunner commandLineRunner () {
		return args -> {
			Student gustavo = new Student(
					null, 
					"Gustavo", 
					"Caldas", 
					"gustavo@test.com", 
					33
			);
			
			Student maria = new Student(
					null, 
					"Maria", 
					"Silva", 
					"meduardalimasilva@test.com", 
					30
			);
			
//			studentRepository.saveAll(
//					List.of(gustavo, maria)
//			);
			
		};
	}
}
