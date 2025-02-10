package com.mahadi.InventoryManagementSystem.service.impl;

import com.mahadi.InventoryManagementSystem.dto.LoginRequestDTO;
import com.mahadi.InventoryManagementSystem.dto.RegisterRequestDTO;
import com.mahadi.InventoryManagementSystem.dto.Response;
import com.mahadi.InventoryManagementSystem.dto.UserDTO;
import com.mahadi.InventoryManagementSystem.enums.UserRole;
import com.mahadi.InventoryManagementSystem.exceptions.InvalidCredentialException;
import com.mahadi.InventoryManagementSystem.exceptions.NotFoundException;
import com.mahadi.InventoryManagementSystem.entity.User;
import com.mahadi.InventoryManagementSystem.repository.UserRepository;
import com.mahadi.InventoryManagementSystem.security.JWTUtils;
import com.mahadi.InventoryManagementSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    /* passwordEncoder: encode (hash) passwords before storing them in the database.
    It’s a common security practice to never store plain-text passwords. */
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JWTUtils jwtUtils; // utility class for handling JWT (JSON Web Token) operations, such as generating and validating tokens.
    @Override
    public Response registerUser(RegisterRequestDTO registerRequestDTO) {
        UserRole role = UserRole.MANAGER; // By default, the user is assigned the role of MANAGER

        if(registerRequestDTO.getRole() != null){
           /* If the RegisterRequestDTO contains a specific role,
           that role is assigned to the user otherwise, the default MANAGER role is assigned. */
        role = registerRequestDTO.getRole();
        }
        User userToSave = User.builder()
                .name(registerRequestDTO.getName())
                .email(registerRequestDTO.getEmail())
                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
                .phoneNumber(registerRequestDTO.getPhoneNumber())
                .role(role)
                .build();

        userRepository.save(userToSave);

        return Response.builder()
                .status(200)
                .message("user created successfully")
                .build();
    }

    @Override
    public Response loginUser(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(()-> new NotFoundException("Email not found"));
        if(!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())){
            throw new InvalidCredentialException("Password does not match");
        }
        String token = jwtUtils.generateToken(user.getEmail());
        return Response.builder()
                .status(200)
                .message("User logged in successfully")
                .role(user.getRole())
                .token(token)
                .expirationTime("6 months")
                .build();
    }

    @Override
    public User getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("User not found"));
        user.setTransactions(null);
        return user;
    }

    @Override
    public Response getAllUser() {
        List<User> userList = userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        userList.forEach(user -> user.setTransactions(null));
        List<UserDTO> userDTOS = modelMapper.map(
                userList, new TypeToken<List<UserDTO>>() {}.getType());

        return Response.builder()
                .status(200)
                .message("Successfully got the list")
                .userList(userDTOS).build();
    }

    @Override
    public Response updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        modelMapper.map(userDTO, existingUser);
        userRepository.save(existingUser);
        return Response.builder()
                .status(200)
                .message("user updated successfully")
                .build();
    }

    @Override
    public Response deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));
        userRepository.deleteById(id);
        return Response.builder()
                .status(200)
                .message("user deleted successfully")
                .build();
    }

    @Override
    public Response getUserTransactions(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.getTransactions().forEach(transactionDTO -> {
            transactionDTO.setUser(null);
            transactionDTO.setSupplier(null);
        });

        return Response.builder()
                .status(200)
                .message("transactions found successfully")
                .user(userDTO)
                .build();
    }
    }

