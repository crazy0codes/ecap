package com.student.ecap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "http://localhost:5173")
@SpringBootApplication
public class EcapApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(EcapApplication.class, args);
	}

}
