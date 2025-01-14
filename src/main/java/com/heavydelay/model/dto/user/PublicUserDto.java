package com.heavydelay.model.dto.user;

import java.time.LocalDateTime;

import com.heavydelay.enums.UserStatus;
import com.heavydelay.model.entity.Roles;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PublicUserDto{

    private Long idUser;
    private Roles role;
    private String name;
    private String lastname;
    private String username;
    private String description;
    private UserStatus status;
    private LocalDateTime lastConnection;
    private LocalDateTime createDate;
}
