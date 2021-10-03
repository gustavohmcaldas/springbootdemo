package com.gustavohmcaldas.studentapi.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gustavohmcaldas.studentapi.domain.CustomUser;
import com.gustavohmcaldas.studentapi.domain.Student;
import com.gustavohmcaldas.studentapi.repository.CustomUserRepository;
import com.gustavohmcaldas.studentapi.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class StudentConfig {

	private final StudentRepository studentRepository;
	private final CustomUserRepository customUserRepository;

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			populateStudentTable();
			
			populateCustomUserTable();
		};
	}

	private void populateCustomUserTable() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		CustomUser admin = new CustomUser(null, "gustavo", "admin", passwordEncoder.encode("test"), "ROLE_USER,ROLE_ADMIN");
		
		CustomUser user = new CustomUser(null, "user", "user", passwordEncoder.encode("test"), "ROLE_USER");
		
		customUserRepository.saveAll(List.of(admin, user));
	}

	private void populateStudentTable() {
		Student james = new Student(null, "James", "Smith", "james@test.com", 33);

		Student robert = new Student(null, "Robert", "Johnson", "robert@test.com", 30);

		Student john = new Student(null, "John", "Williams", "john@test.com", 23);

		Student michael = new Student(null, "Michael", "Brown", "michael@test.com", 21);

		Student william = new Student(null, "William", "Jones", "william@test.com", 36);

		Student david = new Student(null, "David", "Garcia", "david@test.com", 33);

		Student richard = new Student(null, "Richard", "Miller", "richard@test.com", 22);

		Student joseph = new Student(null, "Joseph", "Davis", "joseph@test.com", 21);

		Student thomas = new Student(null, "Thomas", "Harris", "thomas@test.com", 28);

		Student charles = new Student(null, "Charles", "Rodriguez", "charles@test.com", 29);

		Student christopher = new Student(null, "Christopher", "Martinez", "christopher@test.com", 19);

		Student daniel = new Student(null, "Daniel", "Hernandez", "daniel@test.com", 18);

		Student mary = new Student(null, "Mary", "Lopez", "mary@test.com", 19);

		Student patricia = new Student(null, "Patricia", "Gonzales", "patricia@test.com", 28);

		Student jennifer = new Student(null, "Jennifer", "Wilson", "jennifer@test.com", 33);

		Student linda = new Student(null, "Linda", "Anderson", "linda@test.com", 40);

		Student elizabeth = new Student(null, "Elizabeth", "Thomas", "elizabeth@test.com", 22);

		Student barbara = new Student(null, "Barbara", "Taylor", "barbara@test.com", 25);

		Student susan = new Student(null, "Susan", "Moore", "susan@test.com", 27);

		Student jessica = new Student(null, "Jessica", "White", "jessica@test.com", 18);

		Student sarah = new Student(null, "Sarah", "Jackson", "sarah@test.com", 20);

		Student karen = new Student(null, "Karen", "Martin", "karen@test.com", 36);

		Student nancy = new Student(null, "Nancy", "Thompson", "nancy@test.com", 21);

		Student lisa = new Student(null, "Lisa", "Lee", "lisa@test.com", 26);

		Student bob = new Student(null, "Bob", "Perez", "bob@test.com", 22);

		studentRepository.saveAll(List.of(james, robert, john, michael, william, 
											david, richard, joseph, thomas, charles, 
											christopher, daniel, mary, patricia, jennifer, 
											linda, elizabeth, barbara, susan, jessica,
											sarah, karen, nancy, lisa, bob));
	}
}
