package com.openclassrooms.mdd_api.service;

import com.openclassrooms.mdd_api.dto.RegisterRequestDto;
import com.openclassrooms.mdd_api.dto.UserDto;
import com.openclassrooms.mdd_api.exception.ResourceNotFoundException;
import com.openclassrooms.mdd_api.exception.UserAlreadyRegisteredException;
import com.openclassrooms.mdd_api.model.User;

import java.util.Optional;

public interface UserService {

    User addUser(RegisterRequestDto registerRequest) throws UserAlreadyRegisteredException;

    User updateUser(User user);

    Optional<User> findUserByMail(String email);

    User getLoggedUser() throws ResourceNotFoundException;

    UserDto findUser() throws ResourceNotFoundException;

}
