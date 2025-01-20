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

import com.fasterxml.jackson.annotation.JsonView;
import com.heavydelay.model.dto.user.UserReturnDto;
import com.heavydelay.model.dto.user.UserUpdateDto;
import com.heavydelay.model.payload.MessageResponse;
import com.heavydelay.service.IUser;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private IUser userService;

    // Endpoints de tipo GET 
    @GetMapping("/users/{detailed}")
    public ResponseEntity<?> showAllUsers(@PathVariable boolean detailed){
        List<UserReturnDto> users = userService.showAllUsers(detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Users successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(users)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}({detailed})")
    public ResponseEntity<?> showUserById(@PathVariable Long id, @PathVariable boolean detailed) {
        UserReturnDto user = userService.showUserById(id, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User successfully obtained.")
            .status(HttpStatus.OK.value())
            .objectResponse(user)
            .build(), HttpStatus.OK
        );
    }

    // Endpoints de tipo POST
    @PostMapping("/register")
    @JsonView(UserUpdateDto.RegisterUserView.class)
    public ResponseEntity<?> registerNewUser(@RequestBody @Valid UserUpdateDto registerUserDto) {
        UserReturnDto userCreate = userService.registerNewUser(registerUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User registered successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(userCreate)
            .build(), HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    @JsonView(UserUpdateDto.LoginUserView.class)
    public ResponseEntity<?> loginUser(@RequestBody UserUpdateDto loginUserDto){
        UserReturnDto userLogin = userService.loginUser(loginUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("user logged in successfully")
            .status(HttpStatus.OK.value())
            .objectResponse(userLogin)
            .build(), HttpStatus.OK
            );
        }

    // Endpoints de tipo PUT
    @PutMapping("/{id}/update-details")
    @JsonView(UserUpdateDto.AllValuesUpdateView.class)
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto updateUserDto){
        UserReturnDto updateUser = userService.changeUserValuesById(id, updateUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User successfully updated.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateUser)
            .build(), HttpStatus.CREATED
        );
    }
    
    @PutMapping("/{id}/update-password")
    @JsonView(UserUpdateDto.PasswordUpdateView.class)
    public ResponseEntity<?> changeUserPasswordById(@PathVariable Long id, @RequestBody @Valid UserUpdateDto passwordUserDto){
        UserReturnDto updateUser = userService.changeUserPasswordById(id, passwordUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User password changed successfully.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateUser)
            .build(), HttpStatus.CREATED
        );
    }
    
    @PutMapping("/{id}/update-name")
    @JsonView(UserUpdateDto.NameUpdateView.class)
    public ResponseEntity<?> changeUserNameById(@PathVariable Long id, @RequestBody @Valid UserUpdateDto nameUserDto){
        UserReturnDto updateUser = userService.changeUserNameById(id, nameUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User password changed successfully.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateUser)
            .build(), HttpStatus.CREATED
            );
        }
        
    @PutMapping("/{id}/update-lastname")
    @JsonView(UserUpdateDto.LastnameUpdateView.class)
    public ResponseEntity<?> changeUserLastnameById(@PathVariable Long id, @RequestBody @Valid UserUpdateDto lastnameUserDto){
        UserReturnDto updateUser = userService.changeUserLastnameById(id, lastnameUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User password changed successfully.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateUser)
            .build(), HttpStatus.CREATED
        );
    }
    
    @PutMapping("/{id}/update-username")
    @JsonView(UserUpdateDto.UsernameUpdateView.class)
    public ResponseEntity<?> changeUserUsernameById(@PathVariable Long id, @RequestBody @Valid UserUpdateDto usernameUserDto){
        UserReturnDto updateUser = userService.changeUserUsernameById(id, usernameUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User password changed successfully.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateUser)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}/update-description")
    @JsonView(UserUpdateDto.DescriptionUpdateView.class)
    public ResponseEntity<?> changeUserDescriptionById(@PathVariable Long id, @RequestBody @Valid UserUpdateDto descriptionUserDto){
        UserReturnDto updateUser = userService.changeUserDescriptionById(id, descriptionUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User password changed successfully.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateUser)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}/update-status")
    @JsonView(UserUpdateDto.StatusUpdateView.class)
    public ResponseEntity<?> changeUserStatusById(@PathVariable Long id, @RequestBody @Valid UserUpdateDto statusUserDto){
        UserReturnDto updateUser = userService.changeUserStatusById(id, statusUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User password changed successfully.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateUser)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}/update-role")
    @JsonView(UserUpdateDto.RoleUpdateView.class)
    public ResponseEntity<?> changeUserRoleById(@PathVariable Long id, @RequestBody @Valid UserUpdateDto roleUserDto){
        UserReturnDto updateUser = userService.changeUserRoleById(id, roleUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User password changed successfully.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateUser)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/update-email")
    @JsonView(UserUpdateDto.EmailUpdateView.class)
    public ResponseEntity<?> changeUserEmail(@RequestBody @Valid UserUpdateDto emailUserDto){
        UserReturnDto updateEmailUser = userService.changeUserEmail(emailUserDto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User email changed successfully.")
            .status(HttpStatus.CREATED.value())
            .objectResponse(updateEmailUser)
            .build(), HttpStatus.CREATED
        );
     }

    
    // Endpoints de tipo DELETE
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

    // Endpoint para administrador
    @GetMapping("/admin/{id}")
    public ResponseEntity<?> showUserAdminById(@PathVariable Long id) {
        UserReturnDto user = userService.showUserAdminById(id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("User successfully obtained.")
            .status(HttpStatus.OK.value())
            .objectResponse(user)
            .build(), HttpStatus.OK
        );
    }

}

