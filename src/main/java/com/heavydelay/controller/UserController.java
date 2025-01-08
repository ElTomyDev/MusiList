package com.heavydelay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.heavydelay.model.dto.UserDto;
import com.heavydelay.model.payload.MessageResponse;
import com.heavydelay.service.IUser;

import jakarta.validation.Valid;



@RestController
public class UserController {
    
    @Autowired
    private IUser userService;

    @GetMapping("/users")
    public ResponseEntity<?> showAllUsers(){
        return new ResponseEntity<>(userService.showAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id})")
    public ResponseEntity<?> showUserById(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.showUserById(id), HttpStatus.OK);
    }
    
    @PostMapping("/user")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UserDto userDto) {
        UserDto userCreate = userService.createNewUser(userDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Successfully saved.")
            .status(HttpStatus.CREATED.value())
            .object(userCreate)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDto userDto){
        UserDto updateUser = userService.updateUser(userDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User successfully updated.")
            .object(updateUser)
            .status(HttpStatus.CREATED.value())
            .build(), HttpStatus.CREATED
        );
    }
}
