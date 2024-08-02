package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	//1. 암호화/복호화에 사용하는 Bean 등록
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//2. 인증 및 인가
	@Bean
	SecurityFilterChain filterChanin(HttpSecurity http) throws Exception {
		//Security 체크하는 경로 및 각 경로별 권한
		http.authorizeHttpRequests() 
		    .antMatchers("/", "/all")  		// 경로
		        .permitAll()		  		// 권한
		    .antMatchers("/admin/**") 		// 경로    
		    	.hasRole("ADMIN")     		// 권한
		    	// ROLE_ADMIN
		    .antMatchers("/user/**")  		// 경로
		        //.hasAuthority("ROLE_USER") 	// 권한
		        .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
			.anyRequest()
			    .authenticated()
			.and()
			.formLogin()
				.defaultSuccessUrl("/all")
			.and()
			.logout()
				.logoutSuccessUrl("/all");
		
		http.csrf().disable();
		
		return http.build();
	}
	
	// 테스트용 ) 메모리 인증 방식
	//@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user = User.builder()
				               .username("user1")
				               .password(passwordEncoder().encode("1234"))
				               //.authorities("ROLE_USER")
				               .roles("USER") // ROLE_USER
				               .build();
		UserDetails admin = User.builder()
				                .username("admin1")
				                .password(passwordEncoder().encode("1234"))
				                .roles("ADMIN")
				                .build();
		return new InMemoryUserDetailsManager(user, admin);	               
	}
}
