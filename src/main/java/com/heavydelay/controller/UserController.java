package com.heavydelay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.heavydelay.model.dto.UserDto;
import com.heavydelay.model.entity.User;
import com.heavydelay.model.payload.MessageResponse;
import com.heavydelay.service.IUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserController {
    
    @Autowired
    private IUser userService;

    @GetMapping("/users")
    public ResponseEntity<?> showAllUsers(){
        List<User> users = userService.listAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(
                MessageResponse.builder()
                .message("No records found.")
                .object(null)
                .build(), HttpStatus.OK
            );
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id})")
    public ResponseEntity<?> showUserById(@PathVariable Integer id) {
        User user = userService.findById(id);
        if(user==null){
            return new ResponseEntity<>(
                MessageResponse.builder()
                .message("The record you are trying to search for does not exist.")
                .reason("The ID "+ id + " does not exist")
                .object(null)
                .build(), HttpStatus.NOT_FOUND
            );
        }
        UserDto userDto = UserDto.builder()
                        .idUser(user.getIdUser())
                        .name(user.getName())
                        .lastname(user.getLastname())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .description(user.getDescription())
                        .password(user.getPassword())
                        .status(user.getStatus())
                        .createDate(user.getCreateDate())
                        .build();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    
    @PostMapping("/user")
    public ResponseEntity<?> createNewUser(@RequestBody UserDto userDto) {
        User userSave = null;
        try {
            userSave = userService.save(userDto);
            UserDto dtoResponse = UserDto.builder()
                                .idUser(userSave.getIdUser())
                                .name(userSave.getName())
                                .lastname(userSave.getLastname())
                                .username(userSave.getUsername())
                                .email(userSave.getEmail())
                                .description(userSave.getDescription())
                                .password(userSave.getPassword())
                                .status(userSave.getStatus())
                                .createDate(userSave.getCreateDate())
                                .build();
            return new ResponseEntity<>(
                MessageResponse.builder()
                .message("Successfully saved.")
                .object(dtoResponse)
                .build(), HttpStatus.CREATED
            );
        } catch(DataAccessException exDt){
            return new ResponseEntity<>(
              MessageResponse.builder()
              .message(exDt.getMessage())
              .object(null)
              .build(), HttpStatus.METHOD_NOT_ALLOWED
            );
        }
    }
}
