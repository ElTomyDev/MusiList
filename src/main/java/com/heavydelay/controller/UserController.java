package com.heavydelay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
        List<UserDto> users = userService.showAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(
                MessageResponse.builder()
                .message("No records found.")
                .object(users)
                .build(), HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id})")
    public ResponseEntity<?> showUserById(@PathVariable Integer id) {
        if(!userService.existsById(id)){
            return new ResponseEntity<>(
                MessageResponse.builder()
                .message("The record you are trying to search for does not exist.")
                .reason("The ID "+ id + " does not exist")
                .object(null)
                .build(), HttpStatus.NOT_FOUND
            );
        }
        UserDto user = userService.showById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @PostMapping("/user")
    public ResponseEntity<?> createNewUser(@RequestBody @Valid UserDto userDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            bindingResult.getAllErrors().forEach(error -> {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            });

            return new ResponseEntity<>(
            MessageResponse.builder()
                .message("Validation error")
                .reason(errorMessage.toString())
                .object(null)
                .build(), HttpStatus.BAD_REQUEST
            );
        }
        try {
            UserDto userCreate = userService.create(userDto);
            return new ResponseEntity<>(
                MessageResponse.builder()
                .message("Successfully saved.")
                .object(userCreate)
                .build(), HttpStatus.CREATED
            );
        } catch(DataIntegrityViolationException e){
            return new ResponseEntity<>(
              MessageResponse.builder()
              .message("Database error")
              .reason(e.getMessage())
              .object(null)
              .build(), HttpStatus.CONFLICT
            );
        } catch (Exception e){
            return new ResponseEntity<>(
              MessageResponse.builder()
              .message("An error occurred")
              .reason(e.getMessage())
              .object(null)
              .build(), HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDto userDto){
        try{

            if(!userService.existsById(userDto.getIdUser())){
                return new ResponseEntity<>(
                    MessageResponse.builder()
                    .message("Error trying to update user data.")
                    .reason("The ID "+ userDto.getIdUser() + " does not exist.")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND
                );
            }
    
            UserDto updateUser = userService.update(userDto);
            return new ResponseEntity<>(
                    MessageResponse.builder()
                    .message("User successfully updated.")
                    .object(updateUser)
                    .build(), HttpStatus.CREATED
            );
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                MessageResponse.builder()
                    .message("Error updating user data.")
                    .reason("Database constraint violation: " + e.getMessage())
                    .object(null)
                    .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                MessageResponse.builder()
                    .message("An unexpected error occurred.")
                    .reason(e.getMessage())
                    .object(null)
                    .build()
            );
        }

    }
}
