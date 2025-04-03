package com.openclassrooms.mdd_api.service;

import com.openclassrooms.mdd_api.dto.RegisterRequestDto;
import com.openclassrooms.mdd_api.exception.UserAlreadyRegisteredException;
import com.openclassrooms.mdd_api.model.User;
import com.openclassrooms.mdd_api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addUser(RegisterRequestDto registerRequest) throws UserAlreadyRegisteredException {
        log.info("Check if user is already registered");
        this.isUserAlreadyRegister(registerRequest.getEmail());

        User userToSave = User.builder()
                .userName(registerRequest.getUserName())
                .email(registerRequest.getEmail())
                .password(this.passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        log.info("Save user {} in database", userToSave.getEmail());
        return this.userRepository.save(userToSave);
    }

    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByMail(String email) {
        return this.userRepository.findByEmail(email);
    }

    private void isUserAlreadyRegister(String email) throws UserAlreadyRegisteredException {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            log.error("User is already registered");
            throw new UserAlreadyRegisteredException();
        }
    }
}
