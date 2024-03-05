package com.avd.security.exceptions;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.ServletException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.ServerException;
import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = CustomException.class)
//    public ResponseEntity<String> handleCustomException(CustomException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<String> handleSignatureException(final SignatureException ex) {
        return ResponseEntity.badRequest().body("This is a SignatureException");
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<String> handleServletException (final ServletException ex) {
        return ResponseEntity.badRequest().body("This is a ServletException");
    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<String> handleBadCredentialsException (final BadCredentialsException ex) {
//        return ResponseEntity.badRequest().body("This is a BadCredentialsException");
//    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<String> handleMalformedJwtException (final MalformedJwtException ex) {
        return ResponseEntity.badRequest().body("This is a MalformedJwtException");
    }

//    if (ex instanceof BadCredentialsException) {
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
//            errorDetail.setProperty("access_denied_reason", "Authentication Failure");
//        }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(final Exception ex) {
        return ResponseEntity.badRequest().body("This is an Exception");
    }

}
