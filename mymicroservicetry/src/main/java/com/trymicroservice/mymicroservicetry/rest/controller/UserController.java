package com.trymicroservice.mymicroservicetry.rest.controller;

import com.trymicroservice.mymicroservicetry.rest.dto.UserDTO;
import com.trymicroservice.mymicroservicetry.rest.dto.request.MyUserDto;
import com.trymicroservice.mymicroservicetry.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody MyUserDto createOrUpdateUserDTO) {
        return new ResponseEntity(new UserDTO(userService.createUser(createOrUpdateUserDTO)), null, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id) {
        return new UserDTO(userService.getUserById(id));
    }

    @PostMapping("edit/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody MyUserDto updateUserDTO) {
        return new ResponseEntity(new UserDTO(userService.updateUser(id, updateUserDTO)), null, HttpStatus.CREATED);
    }

}
