package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class AopTests {
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void testEncoder() {
		String password = "1234";
		
		String enPwd = passwordEncoder.encode(password);
		System.out.println("match : " + enPwd);
		
		boolean matchResult = passwordEncoder.matches(password, enPwd);
		System.out.println("match : " + matchResult);
	}
}
