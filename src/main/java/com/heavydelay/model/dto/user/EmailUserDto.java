package com.heavydelay.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class EmailUserDto {

    @NotBlank(message = "Old email cannot be empty")
    private String oldEmail;

    @NotBlank(message = "New email cannot be empty")
    @Email(message = "It must be a valid email")
    private String newEmail;
}
