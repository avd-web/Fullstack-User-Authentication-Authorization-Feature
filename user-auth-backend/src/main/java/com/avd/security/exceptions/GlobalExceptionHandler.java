package com.avd.security.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Bad Credential : Authentication Failure : 401
    // Access denied : Authorization ERROR : 403
    // Invalid JWT Signature : 403 : (JWT) SignatureException
    // Token Expired : ExpiredJwtException

    // Todo: add seeder for users to be able to test auth.
    // Todo: write failing tests for register and authentication errors.

    // Test CustomException via DemoController endpoint:
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    // Implementation with ProblemDetail:
    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(final BadCredentialsException ex) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
        errorDetail.setProperty("access_denied_reason", "Authentication Failure");
        return errorDetail;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(final AccessDeniedException ex) {

        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
        errorDetail.setProperty("access_denied_reason", "This is an AccessDeniedException");
        return errorDetail;

    }

    // Implementation with ResponseEntity:
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleSignatureException(final SignatureException ex) {
        return ResponseEntity.badRequest().body("This is a SignatureException");
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<String> handleServletException(final ServletException ex) {
        return ResponseEntity.badRequest().body("This is a ServletException");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ProblemDetail handleExpiredJwtException(final ExpiredJwtException ex) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
        errorDetail.setProperty("access_denied_reason", "JWT token already expired.");
        return errorDetail;
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<String> handleMalformedJwtException(final MalformedJwtException ex) {
        return ResponseEntity.badRequest().body("This is a MalformedJwtException");
    }

    // Generic Exception:
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(final Exception ex) {
        return ResponseEntity.badRequest().body("This is a generic-Exception");
    }

}
