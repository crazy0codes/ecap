package com.student.ecap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:5173")
@SpringBootApplication
public class EcapApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcapApplication.class, args);
	}

	@Bean
	public CommandLineRunner hashPassword(PasswordEncoder passwordEncoder) {
		return args -> {
			String studentPassToHash = "studentpass2";
			String hashedStudentPass = passwordEncoder.encode(studentPassToHash);
			System.out.println("\n------------------------------------------------------------------");
			System.out.println("BCrypt Hash for '" + studentPassToHash + "': " + hashedStudentPass);
			System.out.println("------------------------------------------------------------------\n");
		};
	}
}
