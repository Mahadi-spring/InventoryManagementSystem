package com.mahadi.InventoryManagementSystem.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahadi.InventoryManagementSystem.dto.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component /* In Spring, @Component is an annotation that tells Spring to manage the class as a bean.
A bean is basically an object that Spring will create and control for you, including handling its
lifecycle (creating it, destroying it, etc.). If you marked it as @Component, Spring would treat it
as a "bean," and try to inject it into other parts of your code (like services or controllers).  */

@RequiredArgsConstructor /* This is a Lombok annotation that generates a constructor for all the final fields in the class.
 It simplifies dependency injection by creating a constructor that will automatically inject the ObjectMapper into the class.
This constructor will only include final fields (in this case, objectMapper), so it eliminates the need to write a constructor manually. */


/* This class implements the AuthenticationEntryPoint interface, which allows you to define how to
respond when an authentication exception occurs (e.g., when a user tries to access a protected resource but hasn't logged in).*/
public class CustomAuthEntryPointHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;  // @RequiredArgsConstructor needs for final fields

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        Response errorResponse = Response.builder()
                /* Sets the HTTP status code to 401 (Unauthorized), which indicates that the user is not authenticated. */
                .status(HttpStatus.UNAUTHORIZED.value())
                /* message: Sets the error message from the AuthenticationException. This message typically describes why
                authentication failed, such as "Bad credentials" or "Access denied".*/
                .message(authException.getMessage()).build();
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
