package com.heavydelay.model.dto.user;

import java.time.LocalDateTime;

import com.heavydelay.enums.UserStatus;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class UserDto{

    private Integer idUser;
    private String name;
    private String lastname;
    private String username;
    private String description;
    private UserStatus status;
    private LocalDateTime createDate;
}
