package com.avd.security.advice;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@RestControllerAdvice
public class CustomExceptionHandler {


    // Bad Credential : Authentication Failure : 401
    // Access denied : Authorization ERROR : 403
    // Invalid JWT Signature : 403 : (JWT) SignatureException
    // Token Expired : ExpiredJwtException

    // Todo: add seeder for users to be able to test auth.
    // Todo: write failing tests for register and authentication errors.

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception ex) {

        ProblemDetail errorDetail = null;

        // MalformedJwtException???

        if (ex instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "Authentication Failure");
        }

        if (ex instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "not-authorized");
        }

        if (ex instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT Signature not valid");
        }

        if (ex instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT token already expired");
        }

//        //added myself:
//        if (ex instanceof RuntimeException) {
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
//            errorDetail.setProperty("access_denied_reason", "RUNTIME ERROR");
//        }

        return errorDetail;
    }
}
