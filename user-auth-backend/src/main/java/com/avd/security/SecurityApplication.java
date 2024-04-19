package com.avd.security;

import com.avd.security.auth.AuthenticationService;
import com.avd.security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.avd.security.user.Role.*;

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
    * DataIntegrityViolationException
    * HttpMessageNotReadableException
    * MethodArgumentNotValidException
    *
    * (Refresh-token Expired) : ExpiredJwtException : 401 "Session expired."
    * Is this all?
    *
    * Todo: Validation (Email and password)
    * Done: Strong password validation
    * Done: ConfirmPassword: Password match validation
    *
    * Todo: Generic todos:
    * Done: Added overloaded register method in AuthenticationService, users get User roles automatically while registering via /register endpoint.
    * Todo: rethink how Admin role is used and created, all credentials are now public on GitHub?
    *
    * Done: decision: all error-messages are send as String, with JSON format. refactor error-messages to JSON format instead of String?
    * Done: refactor error-messages to the correct error for front-end users.
    *
    * Done: implement user-DTO
    * Todo: implement email-verification for register
    * Done: decision: Deleted the "Alibou" named map in target.classes.com. Why is there an folder named "alibou" in target/classes/com folder?
    * Done: add user-seeder.
    * Todo: write tests for register and authentication errors.
    * Todo: test logout functionality.
    * Todo: test refresh token functionality.
    * Todo: verify the correct access and refresh token expiration dates.
    * Todo: verify if secret-key in application.yml, and other (secret)keys are safe.
    * Todo: test Github/VCS and online presence for security/personal leaks.
    *
    * Todo: complete end to end testing for user-auth module.
    *
    * Todo: refactor code to correct code-standard
    * Todo: fix errors "something is never used" in intelliJ.
    *
	 */

	/*
	* SETUP EXTRA (FRONTEND)
	* Installed react-router-dom
	* installed react icons
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
					.build();
			System.out.println("Admin token: " + service.register(admin, ADMIN).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Manager")
					.lastname("Manager")
					.email("manager@mail.com")
					.password("password")
					.build();
			System.out.println("Manager token: " + service.register(manager, MANAGER).getAccessToken());

			var user = RegisterRequest.builder()
					.firstname("User")
					.lastname("User")
					.email("user@mail.com")
					.password("password")
					.build();
			System.out.println("User token: " + service.register(user, USER).getAccessToken());

		};
	}
}
