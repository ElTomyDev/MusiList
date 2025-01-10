package com.heavydelay.model.dto.user;

import com.heavydelay.validation.ValidPassword;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordUserDto {

    @NotBlank(message = "The 'Old Password' cannot be empty")
    private String oldPasword;

    @NotBlank(message = "The 'New Password' cannot be empty")
    @ValidPassword
    private String newPasword;
}
