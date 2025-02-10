package com.mahadi.InventoryManagementSystem.security;

import com.mahadi.InventoryManagementSystem.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
/* The UserDetails interface is used by Spring Security to represent the user who is logging in */
public class AuthUser implements UserDetails {

    private User user;

    @Override
    /* getAuthorities(): This method returns a collection of GrantedAuthority objects, which represent the roles or permissions the user has.*/
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
        /* 1. SimpleGrantedAuthority() takes a String. The SimpleGrantedAuthority is a Spring Security class that
        represents an authority (i.e., a role or permission).
         2. user.getRole().name()): This is calling the getRole() method of the User object,
         which presumably returns the user's role (like ADMIN, USER, etc.). The .name() method of Enum converts
         the role (which might be an enum) into a string, like "ADMIN" or "USER".
         3. List.of(...): This creates an immutable list with the single SimpleGrantedAuthority. It ensures that the returned authorities are unmodifiable.
         */
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
