package com.avd.security.exceptions;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
//import jakarta.servlet.ServletException;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.AccessException;
import java.rmi.ServerException;
//import java.security.SignatureException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<String> handleCustomException(final CustomException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleException(final Exception ex) {
        return ResponseEntity.badRequest().body("This is an Exception");
    }

    // With ResponseEntity
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<String> handleBadCredentialsException (final BadCredentialsException ex) {
//        return ResponseEntity.badRequest().body("This is a BadCredentialsException");
//
//    }

    // With ProblemDetail:
    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handleBadCredentialsException(final BadCredentialsException ex) {
        ProblemDetail errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
        errorDetail.setProperty("access_denied_reason", "Authentication Failure");
        return errorDetail;
    }

//            if (ex instanceof BadCredentialsException) {
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
//            errorDetail.setProperty("access_denied_reason", "Authentication Failure");
//        }
//
//    @ExceptionHandler(value = SignatureException.class)
//    public ResponseEntity<String> handleSignatureException(SignatureException ex) {
//        return ResponseEntity.badRequest().body("This is a SignatureException");
//    }

//    @ExceptionHandler(value = AccessDeniedException.class)
//    public ResponseEntity<String> handleAccessDeniedException(final AccessDeniedException ex) {
//        return ResponseEntity.badRequest().body("This is an AccessDeniedException");
//    }



//    @ExceptionHandler(ServletException.class)
//    public ResponseEntity<String> handleServletException (final ServletException ex) {
//        return ResponseEntity.badRequest().body("This is a ServletException");
//    }

//    @ExceptionHandler(MalformedJwtException.class)
//    public ResponseEntity<String> handleMalformedJwtException (final MalformedJwtException ex) {
//        return ResponseEntity.badRequest().body("This is a MalformedJwtException");
//    }

//    if (ex instanceof BadCredentialsException) {
//            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
//            errorDetail.setProperty("access_denied_reason", "Authentication Failure");
//        }

}
