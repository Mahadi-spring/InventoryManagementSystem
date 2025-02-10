package com.mahadi.InventoryManagementSystem.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahadi.InventoryManagementSystem.dto.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
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


/* The AccessDeniedHandler is responsible for handling access-denied situations,
typically when a user tries to access a resource they don't have permission to access.*/
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;  // @RequiredArgsConstructor needs for final fields
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        Response errorResponse = Response.builder()
                /* status: The HTTP status code is set to 403 (Forbidden),
                which indicates that the user does not have permission to access the resource */
                .status(HttpStatus.FORBIDDEN.value())
                /* message: The message of the AccessDeniedException is used to give more details about why access was denied.*/
                .message(accessDeniedException.getMessage()).build();
        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
