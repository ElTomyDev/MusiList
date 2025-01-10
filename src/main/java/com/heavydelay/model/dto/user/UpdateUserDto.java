package com.heavydelay.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class UpdateUserDto {

    @NotBlank(message = "'name' cannot be empty")
    @Size(min = 2, max = 50, message = "The 'name' must be between 2 and 50 characters long")
    private String name;

    @NotBlank(message = "The 'lastname' cannot be empty")
    @Size(min = 2, max = 50, message = "The 'lastname' must be between 2 and 50 characters long")
    private String lastname;

    @NotBlank(message = "The 'username' cannot be empty")
    @Size(min = 2, max = 50, message = "The 'username' must be between 2 and 50 characters long")
    private String username;

    @NotNull(message = "The 'description' is required")
    @Size(max = 255, message = "The 'description' cannot exceed 255 characters")
    private String description;

}
