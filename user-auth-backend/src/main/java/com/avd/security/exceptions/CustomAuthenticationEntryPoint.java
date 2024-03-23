package com.avd.security.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("customAuthenticationEntryPoint")
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        ResponseEntity<RestError> errorResponse = createErrorResponse();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(errorResponse.getStatusCode().value());
        objectMapper.writeValue(response.getOutputStream(), errorResponse.getBody());
    }

    private ResponseEntity<RestError> createErrorResponse() {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = "Authentication failed";
        return new ResponseEntity<>(new RestError(status.toString(), message), status);
    }
}

record RestError(String status, String message) {

}
