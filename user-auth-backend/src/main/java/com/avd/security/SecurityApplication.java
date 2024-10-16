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
	* (incorrect username/password) BadCredentialsException : 401 "Email and Password don't match."
    * (unauthorized for admin route) AccessDeniedException : 403 "Access denied."
    * (unauthorized for manager route) ServletException : 403 "Access denied."
    * (invalid JWT) SignatureException : 401 "Authentication failed, invalid token."
    * (malformed JWT) MalformedJwtException : 401 "Authentication failed, malformed token."
    * (empty JWT) customAuthenticationEntryPoint : 401 "Authentication failed, no token found."
    * (double register, register a user that already exist) SQLException : 409(conflict) "Email already exist."
    * DataIntegrityViolationException
    * HttpMessageNotReadableException
    * MethodArgumentNotValidException
    * (refresh-token expired) : ExpiredJwtException : 401 "Session expired."
    * Is this all?
    *
    * Todo: Validation (Email and password) part 1, first implementation of user-input-validations.
    * Done: Strong password validation
    * Done: ConfirmPassword: Password match validation
    * Todo: Problem: un-enabled accounts CAN already make requests to /demo-user/user directly after registering but without enabling account via email-check
    * Todo: validation (Email and password) part 2, check and complete 100% safety for online environment.
    *
    * Todo: Generic todos:
    * Done: added overloaded register method in AuthenticationService, users get User roles automatically while registering via /register endpoint.
    * Todo: rethink how Admin role is used and created, all credentials are now public on GitHub?
    *
    * Done: decision: all error-messages are send as String, with JSON format. refactor error-messages to JSON format instead of String?
    * Done: refactor error-messages to the correct error for front-end users.
    *
    * Done: implement user-DTO
    * Todo: implement email-verification for register part 1, send email with confirmation token.
    * Todo: implement email-verification for register part 2, check confirmation token on auth, resend token.
    * Done: decision: Deleted the "Alibou" named map in target.classes.com. Why is there an folder named "alibou" in target/classes/com folder?
    * Done: add user-seeder.
    * Todo: check for correct error messages in front-end.
    * Todo: write tests for register and authentication errors.
    * Todo: implement logout functionality (also in frontend)
    * Todo: test logout functionality.
    * Todo: implement refresh token functionality (also in frontend)
    * Todo: document refresh token implementation
    * Todo: test refresh token functionality.
    * Todo: verify the correct access and refresh token expiration dates.
    * Todo: verify if secret-key in application.yml, and other (secret)keys are safe.
    * Todo: test Github/VCS and online presence for security/personal leaks.
    *
    * Todo: refactor code to correct code-standard
    * Todo: fix warnings/errors "something is never used" in intelliJ.
    * Todo: write and add explanatory comments, quotes and sources missing in the code.
    *
    * Todo: what are licences?
    * Todo: what are the possibilities to remove Lombok?
    * Todo: reduce, reuse, refactor (recycle).
    *
    * Todo: END-TO-END testing for user-auth-feature.
    * Todo: document UML (show app functionality)
    * Todo: backup and order all files for document project completion.
    * Todo: FINISH!
    *
    *
	 */

	/*
	 * SETUP EXTRA (BACKEND)
	 * added dependency: spring-boot-starter-mail
	 * added dependency: <groupId>com.sun.mail</groupId>
            			<artifactId>jakarta.mail</artifactId>
            			<version>1.6.3</version>
	 *
	 * SMTP (mail) server info:
	 * installed maildev via: $ npm install -g maildev
	 * run maildev during development via: $ maildev
	 * open maildev url: http://127.0.0.1:1080/#/ (http://0.0.0.0:1080/ NOT WORKING)
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
