package com.gustavohmcaldas.studentapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavohmcaldas.studentapi.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	List<Student> findByFirstName(String firstName);
}
