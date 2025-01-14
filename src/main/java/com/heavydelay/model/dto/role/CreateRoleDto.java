package com.heavydelay.model.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class CreateRoleDto {

    @NotBlank(message = "The role name must not be empty")
    private String roleName;

}
