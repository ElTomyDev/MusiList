package com.heavydelay.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.heavydelay.enums.UserStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class UserDto implements Serializable{

    @NotNull(message = "The ID must be null when creating a user")
    private Integer idUser;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "The name must be between 2 and 50 characters long")
    private String name;

    @NotBlank(message = "The lastname cannot be empty")
    @Size(min = 2, max = 50, message = "The lastname must be between 2 and 50 characters long")
    private String lastname;

    @NotBlank(message = "The username cannot be empty")
    @Size(min = 2, max = 50, message = "The username must be between 2 and 50 characters long")
    private String username;

    @NotBlank(message = "The email cannot be empty")
    @Email(message = "It must be a valid email")
    private String email;

    @Size(max = 255, message = "The description cannot exceed 255 characters")
    private String description;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotNull(message = "User status is required")
    private UserStatus status;

    @PastOrPresent(message = "The creation date cannot be in the future")
    private LocalDateTime createDate;

}
