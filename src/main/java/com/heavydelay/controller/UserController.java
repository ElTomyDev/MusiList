package com.heavydelay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavydelay.model.dto.user.EmailUserDto;
import com.heavydelay.model.dto.user.LoginUserDto;
import com.heavydelay.model.dto.user.PasswordUserDto;
import com.heavydelay.model.dto.user.PublicUserDto;
import com.heavydelay.model.dto.user.RegisterUserDto;
import com.heavydelay.model.dto.user.UpdateUserDto;
import com.heavydelay.model.payload.MessageResponse;
import com.heavydelay.service.IUser;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private IUser userService;

    @GetMapping("/users")
    public ResponseEntity<?> showAllUsers(){
        List<PublicUserDto> users = userService.showAllUsers();
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Users successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(users)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showUserById(@PathVariable Long id) {
        PublicUserDto user = userService.showUserById(id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User successfully obtained.")
            .status(HttpStatus.OK.value())
            .objectResponse(user)
            .build(), HttpStatus.OK
        );
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid RegisterUserDto registerUserDto) {
        PublicUserDto userCreate = userService.registerNewUser(registerUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User registered successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(userCreate)
            .build(), HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserDto loginUserDto){
        PublicUserDto userLogin = userService.loginUser(loginUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("user logged in successfully")
            .status(HttpStatus.OK.value())
            .objectResponse(userLogin)
            .build(), HttpStatus.OK
        );
    }

    @PutMapping("/{id}/update-details")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDto updateUserDto){
        PublicUserDto updateUser = userService.changeUserValues(id, updateUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User successfully updated.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateUser)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}/update-password")
    public ResponseEntity<?> changeUserPassword(@PathVariable Long id, @RequestBody @Valid PasswordUserDto passwordUserDto){
        PasswordUserDto updateUser = userService.changeUserPassword(id, passwordUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User password changed successfully.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateUser)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/update-email")
    public ResponseEntity<?> changeUserPassword(@RequestBody @Valid EmailUserDto emailUserDto){
        EmailUserDto updateEmailUser = userService.changeUserEmail(emailUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User email changed successfully.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateEmailUser)
            .build(), HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User delete successfully.")
            .status(HttpStatus.OK.value())
            .objectResponse("user with ID '" + id + "'' deleted")
            .build(), HttpStatus.OK
        );
    }
}
