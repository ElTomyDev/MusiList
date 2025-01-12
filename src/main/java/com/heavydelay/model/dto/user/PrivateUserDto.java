package com.heavydelay.model.dto.user;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "password")
@Builder
public class PrivateUserDto {
    private String email;
    private String password;
}
