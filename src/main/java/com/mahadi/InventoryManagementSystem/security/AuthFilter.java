package com.mahadi.InventoryManagementSystem.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
/*OncePerRequestFilter: This is a base class provided by Spring Security for filters that should run only once per request.

 */
    private final JWTUtils jwtUtils; //  A utility class for working with JWTs (e.g., validating tokens, extracting claims).
    private final CustomeUserDetailService customeUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
    String token = getTokenFromRequest(request); // extract the token
     if(token != null){
         String email = jwtUtils.generateToken(token);
         UserDetails userDetails = customeUserDetailService.loadUserByUsername(email);
         if(StringUtils.hasText(email) && jwtUtils.isTokenValid(token, userDetails)){  // If email is not empty then validate the token
                log.info("token is valid, {}", email);

             UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                     null, userDetails.getAuthorities()); // if the token is valid, the user is authenticated.

             // Adds details about the request (e.g., IP address, session ID) to the authenticationToken object
             authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

             //The user is authenticated, and their details are stored in the SecurityContext.
             SecurityContextHolder.getContext().setAuthentication(authenticationToken);
         }
     }
     try {
         filterChain.doFilter(request, response);
     }catch (Exception e){
         log.error("Error occured in AuthFiler: {}", e.getMessage() );
     }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        /* This method extracts a JWT (JSON Web Token) from an HTTP request. Typically, JWTs are sent in the
        Authorization header of an HTTP request, prefixed with the word Bearer. This method retrieves the token
        from the header and removes the Bearer prefix to return just the token.

        HttpServletRequest request: This is an object that represents the HTTP request
        sent by the client (e.g., a browser or a mobile app).
        It contains all the information about the request, such as headers, parameters, and the body. */
        String tokenWithBearer = request.getHeader("Authorization"); // retrieves the value of the Authorization header from the HTTP request.
        if (tokenWithBearer != null && tokenWithBearer.startsWith("Bearer ")){
            return tokenWithBearer.substring(7);
            // The substring(7) method removes the first 7 characters (Bearer ) from the string, leaving only the token.
        }
        return null;
    }


}
