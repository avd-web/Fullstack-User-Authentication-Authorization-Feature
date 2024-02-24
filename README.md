# User login feature (authentication and authorization)

___
## "A feature for authenticating and authorizing website users."  
_Made by : avd-web (https://github.com/avd-web) _
___

## User-story
_"As a website user, I want to be able to securely log in to the website using my username and password, and be granted access to resources and functionalities based on my assigned roles, so that I can interact with the website and perform authorized actions."_


### Acceptance Criteria
#### Registration:
Users can register for an account on the website by providing their username, email address, password, and any other required information.
Passwords are securely stored using a one-way hashing algorithm.
#### Login:
Users can attempt to log in to the website using their registered username and password.
Login attempts are validated against the stored user credentials.
Upon successful login, a secure session is established and a token is issued to the user.
#### Authorization:
Users are assigned roles based on their account type or other criteria.
Access to specific resources and functionalities on the website is controlled based on user roles.
Users can only access resources and perform actions that are authorized for their assigned roles.
Unauthorized access attempts are denied and appropriate error messages are displayed.
#### Security:
Communication between the user and the website is encrypted using HTTPS.
User sessions are managed securely and invalidated after a period of inactivity.
The application is implemented using secure coding practices to prevent common vulnerabilities.

#### Technical Implementation:
The application will be developed using Java with Spring Boot framework.
Spring Web will be used to handle web requests and responses.
Spring Data JPA will be used to interact with a relational database for user data storage.
Spring Security version 6 will be used for user authentication, authorization, and session management.
Password hashing will be implemented using a secure algorithm like BCrypt.
JSON Web Tokens (JWT) can be used for secure session management and authorization.

#### Additional Considerations:
Implement mechanisms for password reset and account recovery.
Integrate with social login providers (optional).
Implement multi-factor authentication for additional security (optional).
Log and monitor user login activity for security purposes.
This user story provides a high-level overview of the requirements for a user login and authorization feature. The specific implementation details may vary depending on the specific needs of the application.
___

### Feature-stack
#### Back-end:
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL
- Lombok
- JSON Web Tokens (JWT)
- BCrypt
- Maven

#### Front-end:
- Vite
- Typescript

#### IDE's:
- Intellij Community Edition

#### VCS:
- Git
- GitHub

___

#### Wireframes
add...

___

# Basic setup
___

_**"This features basic setup includes the implementation of security using Spring Boot 3.0 and JSON Web Tokens (JWT)."**_  

It includes the following features:

#### Features
* User registration and login with JWT authentication
* Password encryption using BCrypt
* Role-based authorization with Spring Security
* Customized access denied handling
* Logout mechanism
* Refresh token

#### Technologies
* Spring Boot 3.0
* Spring Security
* JSON Web Tokens (JWT)
* BCrypt
* Maven

#### Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+


To build and run the project, follow these steps:

* Clone the repository.
* Navigate to the project directory: cd spring-boot-security-jwt
* Add database "jwt_security" to postgres
* Build the project: mvn clean install
* Run the project: mvn spring-boot:run

-> The application will be available at http://localhost:8080.
___




