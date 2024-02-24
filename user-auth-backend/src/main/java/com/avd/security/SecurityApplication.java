package com.avd.security;

import com.avd.security.auth.AuthenticationService;
import com.avd.security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.avd.security.user.Role.ADMIN;
import static com.avd.security.user.Role.MANAGER;

@SpringBootApplication
public class SecurityApplication {

	/*
	* SETUP COMPLETED: Basic backend security implementation
	* from: https://github.com/ali-bouali/spring-boot-3-jwt-security
	*
	* TESTED MANUALLY security setup (2024-02-24):
	* REGISTER:	{"firstname" : "Arjan","lastname" : "van D","email" : "avd@mail.com","password" : "TEST1234","role" : "USER"}
	* POST http://localhost:8080/api/v1/auth/authenticate
	* GET http://localhost:8080/api/v1/demo-controller
	* GET http://localhost:8080/api/v1/management
	* GET http://localhost:8080/api/v1/admin
	* POST http://localhost:8080/api/v1/auth/refresh-token
	*
	* NOTES / PROBLEMS:
	* User can be registered without a role, request requires a "role" : "USER".
	* Users can be registered double, which breaks up the authentication process.
	* No input validations.
	* No error handling or error messages.
	* No tests available for testing authentication or authorization.
	 */

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}
}
