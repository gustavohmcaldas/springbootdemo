package com.gustavohmcaldas.studentapi.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gustavohmcaldas.studentapi.service.CustomUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomUserService customUserService;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
//		        csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		        .authorizeRequests()
		        .antMatchers("/api/v1/students/admin/**").hasRole("ADMIN")
                .antMatchers("/api/v1/students/**").hasRole("USER")
                .antMatchers("/api/v1/actuator/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder.encode("123"))
//                .roles("USER", "ADMIN")
//                .and()
//                .withUser("user")
//                .password(passwordEncoder.encode("123"))
//                .roles("USER");
        
        auth.userDetailsService(customUserService)
        	.passwordEncoder(passwordEncoder);
    }
}
