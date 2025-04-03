package com.openclassrooms.mdd_api.service;

import com.openclassrooms.mdd_api.dto.RegisterRequestDto;
import com.openclassrooms.mdd_api.exception.UserAlreadyRegisteredException;
import com.openclassrooms.mdd_api.model.User;

import java.util.Optional;

public interface UserService {

    User addUser(RegisterRequestDto registerRequest) throws UserAlreadyRegisteredException;

    Optional<User> findUserByMail(String email);

}
