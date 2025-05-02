package com.openclassrooms.mdd_api.service;

import com.openclassrooms.mdd_api.dto.RegisterRequestDto;
import com.openclassrooms.mdd_api.dto.UserDto;
import com.openclassrooms.mdd_api.exception.PasswordNotSecureException;
import com.openclassrooms.mdd_api.exception.ResourceNotFoundException;
import com.openclassrooms.mdd_api.exception.UserAlreadyRegisteredException;
import com.openclassrooms.mdd_api.model.User;

import java.util.Optional;

public interface UserService {

    User addUser(RegisterRequestDto registerRequest) throws UserAlreadyRegisteredException, PasswordNotSecureException;

    User updateUser(User user);

    User updateUser(UserDto user) throws UserAlreadyRegisteredException, ResourceNotFoundException, PasswordNotSecureException;

    Optional<User> findUserByMail(String email);

    User getLoggedUser() throws ResourceNotFoundException;

    UserDto findUser() throws ResourceNotFoundException;

    Boolean isEmailAlreadyTaken(String userMail);

    Boolean isUserNameAlreadyTaken(String userName);

}
