package com.heavydelay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavydelay.model.dto.UserDto;
import com.heavydelay.model.dto.validation.ValidationUserDto;
import com.heavydelay.model.payload.MessageResponse;
import com.heavydelay.service.IUser;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private IUser userService;

    @GetMapping("/users")
    public ResponseEntity<?> showAllUsers(){
        List<UserDto> users = userService.showAllUsers();
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Users successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(users)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> showUserById(@PathVariable Integer id) {
        UserDto user = userService.showUserById(id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User successfully obtained.")
            .status(HttpStatus.OK.value())
            .objectResponse(user)
            .build(), HttpStatus.OK
        );
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid ValidationUserDto validUserDto) {
        UserDto userCreate = userService.registerNewUser(validUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User created successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(userCreate)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid ValidationUserDto validUserDto){
        UserDto updateUser = userService.updateUser(validUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User successfully updated.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateUser)
            .build(), HttpStatus.CREATED
        );
    }
}
