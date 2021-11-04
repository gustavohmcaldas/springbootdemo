package com.gustavohmcaldas.studentapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavohmcaldas.studentapi.domain.CustomUser;

public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

    CustomUser findByUsername(String username);
}
