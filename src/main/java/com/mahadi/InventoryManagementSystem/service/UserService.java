package com.mahadi.InventoryManagementSystem.service;

import com.mahadi.InventoryManagementSystem.dto.LoginRequestDTO;
import com.mahadi.InventoryManagementSystem.dto.RegisterRequestDTO;
import com.mahadi.InventoryManagementSystem.dto.Response;
import com.mahadi.InventoryManagementSystem.dto.UserDTO;
import com.mahadi.InventoryManagementSystem.entity.User;


public interface UserService {
    Response registerUser (RegisterRequestDTO registerRequestDTO);
    Response loginUser (LoginRequestDTO loginRequestDTO);
    User getCurrentLoggedInUser ();
    Response getAllUser();
    Response updateUser(Long id, UserDTO userDTO);
    Response deleteUser (Long id);
    Response getUserTransactions(Long id);

}
