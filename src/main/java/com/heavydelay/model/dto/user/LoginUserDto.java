package com.heavydelay.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "password")
@Builder
public class LoginUserDto {

    @NotBlank(message = "The 'email' cannot be empty")
    @Email(message = "It must be a valid 'email'")
    private String email;

    @NotBlank(message = "'password' cannot be empty")
    private String password;
}
