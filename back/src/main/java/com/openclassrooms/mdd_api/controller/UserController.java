package com.openclassrooms.mdd_api.controller;

import com.openclassrooms.mdd_api.dto.UserDto;
import com.openclassrooms.mdd_api.exception.ResourceNotFoundException;
import com.openclassrooms.mdd_api.exception.UserAlreadyRegisteredException;
import com.openclassrooms.mdd_api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    ResponseEntity<?> updateUser(@RequestBody UserDto user) throws ResourceNotFoundException, UserAlreadyRegisteredException {
        log.info("GET api/user called -> start the process to update user");
        this.userService.updateUser(user);
        log.info(("User updated successfully"));
        return ResponseEntity.ok().build();
    }
}
