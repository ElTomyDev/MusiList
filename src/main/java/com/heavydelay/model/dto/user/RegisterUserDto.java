package com.heavydelay.model.dto.user;

import com.heavydelay.validation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "password")
@Builder
public class RegisterUserDto {

    @NotBlank(message = "'name' cannot be empty")
    @Size(min = 2, max = 50, message = "The 'name' must be between 2 and 50 characters long")
    private String name;

    @NotBlank(message = "The 'lastname' cannot be empty")
    @Size(min = 2, max = 50, message = "The 'lastname' must be between 2 and 50 characters long")
    private String lastname;

    @NotBlank(message = "The 'username' cannot be empty")
    @Size(min = 2, max = 50, message = "The 'username' must be between 2 and 50 characters long")
    private String username;

    @NotBlank(message = "The 'email' cannot be empty")
    @Email(message = "It must be a valid 'email'")
    private String email;

    @NotBlank(message = "'password' cannot be empty")
    @ValidPassword
    private String password;

}
