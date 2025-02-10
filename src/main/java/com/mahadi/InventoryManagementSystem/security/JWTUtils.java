package com.mahadi.InventoryManagementSystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
public class JWTUtils {

    private static final long EXPIRATION_TIME_IN_MILISECOND = 100L * 60L * 24L * 30L * 6L;  // expires in 6 months

    private SecretKey key;

    @Value("${secretJwtString}")
    private String secretJwtString;

    @PostConstruct
    private void init(){
        byte[] keyByte = secretJwtString.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keyByte, "HmacSHA256");
    }

    public String generateToken(String email){
        return Jwts.builder().subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME_IN_MILISECOND))
                .signWith(key).compact();
    }

    public String getUserNameFromToken (String token){
        return extractClaims(token, Claims::getSubject);
    }


    private <T> T extractClaims (String token, Function<Claims, T> claimsTFunction){
        return claimsTFunction.apply(Jwts.parser()
                .verifyWith(key).build()
                .parseSignedClaims(token).getPayload());
        /* extractClaims: A generic method that extracts a specific claim from a JWT using a function.
        claimsTFunction: It specifies what kind of claim you want to extract. The method returns
        the value of type T that was extracted by the claimsTFunction
        For example, it could be a function that extracts the expiration time, the username, etc.    */
    }

    public boolean isTokenValid (String token, UserDetails userDetails){
        final String userName = getUserNameFromToken(token); //This function extracts the username (or subject) from the JWT token.
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
        /* 1. userName.equals(userDetails.getUsername(): It checks if the extracted username from the token
        matches the username of the userDetails provided. This ensures that the token corresponds to the correct user.

        2. !isTokenExpired(token): This calls the isTokenExpired method to check if the token is still valid by ensuring
         it hasn't expired. The ! negates the result (i.e., the token is valid only if it is not expired).

        3. This method returns true if:
            The username from the token matches the userDetails.getUsername().
            The token has not expired.     */
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
        /* Claims::getExpiration = claims -> claims.getExpiration()
        This is a method reference that points to the getExpiration method of the Claims class.

        extractClaims(token, Claims::getExpiration): returns a Date representing the expiration date of the token.
        This is extracting the expiration date from the token.
        The extractClaims method typically parses the JWT and retrieves the payload (claims),
        which contains information like expiration time, username, etc.

        .before(new Date()): Returns boolean. This checks if the expiration date of the token is before the current date/time.
        The new Date() represents the current time when the check is made. If the expiration time of the token
        is earlier than the current date, then the token is expired.
        */
    }
}
