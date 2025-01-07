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
import org.springframework.web.bind.annotation.RestController;

import com.heavydelay.exception.ResourceNotFoundException;
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
        List<UserDto> users = userService.showAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(
                MessageResponse.builder()
                .message("No records found.")
                .object(users)
                .build(), HttpStatus.OK
            );
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id})")
    public ResponseEntity<?> showUserById(@PathVariable Integer id) {
        if(!userService.existsById(id)){
            throw new ResourceNotFoundException("The user with ID '" + id + "' was not found");
        }
        UserDto user = userService.showById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @PostMapping("/user")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UserDto userDto) {
        UserDto userCreate = userService.create(userDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Successfully saved.")
            .object(userCreate)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDto userDto){
            
        if(!userService.existsById(userDto.getIdUser())){
            throw new ResourceNotFoundException("The user with ID '" + userDto.getIdUser() + "' was not found");
        }
        
        UserDto updateUser = userService.update(userDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User successfully updated.")
            .object(updateUser)
            .build(), HttpStatus.CREATED
        );

    }
}
