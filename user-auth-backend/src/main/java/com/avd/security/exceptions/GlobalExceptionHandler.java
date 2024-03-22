package com.avd.security.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Test CustomException via DemoController endpoint:
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialsException(final BadCredentialsException ex) {
        return ResponseEntity.status(401).body("{\"status\": \"401 UNAUTHORIZED\", \"message\":\"Email and Password don't match.\"}");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(final AccessDeniedException ex) {
        return ResponseEntity.status(403).body("{\"status\": \"403 FORBIDDEN\", \"message\":\"Access denied.\"}");
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleSignatureException(final SignatureException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"status\": \"401 UNAUTHORIZED\", \"message\":\"Authentication failed, invalid token.\"}");
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<String> handleServletException(final ServletException ex) {
        return ResponseEntity.status(403).body("{\"status\": \"403 FORBIDDEN\", \"message\":\"Access denied.\"}");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpiredJwtException(final ExpiredJwtException ex) {
        return ResponseEntity.status(401).body("{\"status\": \"401 UNAUTHORIZED\", \"message\":\"Session expired.\"}");
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<String> handleMalformedJwtException(final MalformedJwtException ex) {
        return ResponseEntity.status(401).body("{\"status\": \"401 UNAUTHORIZED\", \"message\":\"Authentication failed, malformed token.\"}");
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(final SQLException ex) {
        return ResponseEntity.status(409).body("{\"status\": \"409 CONFLICT\", \"message\":\"Email already exists.\"}");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

//    // Generic Exception: REMOVED, because this generic-exception overrides SQLException for some reason.
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(final Exception ex) {
//        return ResponseEntity.badRequest().body("This is a generic-Exception");
//    }

}
