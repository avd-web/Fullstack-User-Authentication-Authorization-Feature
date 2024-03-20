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
	*
	* NOTES / PROBLEMS:
	*/

	/*
	* Todo: Exception handling:
	* (Incorrect username/password) BadCredentialsException : 401 "Email and Password don't match."
    * (Unauthorized for admin route) AccessDeniedException : 403 "Access denied."
    * (Unauthorized for manager route) ServletException : 403 "Access denied."
    * (Invalid JWT) SignatureException : 401 "Authentication failed, invalid token."
    * (Malformed JWT) MalformedJwtException : 401 "Authentication failed, malformed token."
    * (Empty JWT) customAuthenticationEntryPoint : 401 "Authentication failed, no token found."
    * (double register, register a user that already exist) SQLException : 409(conflict) "Email already exist."
    *
    * (Refresh-token Expired) : ExpiredJwtException : 401 "Session expired."
    * Is this all?
    *
    * Todo: refactor register request, how do users get their user role, register request requires "ROLE" : "USER"?
    * Todo: refactor register request, how do users get their manager role?
    * Todo: rethink how Admin role is used and created, all credentials are now public on GitHub?
    *
    * Todo: refactor error-messages to JSON format instead of String?
    * Todo: refactor error-messages to the correct error for front-end users.
    *
    * Todo: implement user-DTO
    * Todo: answer, why is there an folder named "alibou" in target/classes/com folder?
    * Todo: add user-seeder.
    * Todo: write tests for register and authentication errors.
    * Todo: test logout functionality.
    * Todo: test refresh token functionality.
    * Todo: verify the correct access and refresh token expiration dates.
    * Todo: verify if secret-key in application.yml, and other (secret)keys are safe.
    * Todo: test Github/VCS and online presence for security/personal leaks.
    * Todo: complete end to end testing for user-auth module.
    *
    * Todo: refactor code to correct code-standard
    * Todo: fix errors "something is never used" in intelliJ.
    *
	 */

	/*
	 * SETUP COMPLETED: Basic frontend implementation with Vite.
	 * framework: React
	 * variant: Typescript + SWC
	 */

	/*
	 * SETUP COMPLETED: Basic backend Spring-Security implementation
	 * from: https://github.com/ali-bouali/spring-boot-3-jwt-security
	 *
	 * TESTED MANUALLY security setup (2024-02-24):
	 * REGISTER:	{"firstname" : "Arjan","lastname" : "van D","email" : "avd@mail.com","password" : "TEST1234","role" : "USER"}
	 * POST http://localhost:8080/api/v1/auth/authenticate
	 * GET http://localhost:8080/api/v1/demo-controller
	 * GET http://localhost:8080/api/v1/management
	 * GET http://localhost:8080/api/v1/admin
	 * POST http://localhost:8080/api/v1/auth/refresh-token
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
