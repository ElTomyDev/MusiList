package com.heavydelay.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.heavydelay.enums.UserStatus;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class UserDto implements Serializable{

    private Integer idUser;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String description;
    private String password;
    private UserStatus status = UserStatus.ACTIVE;
    private LocalDateTime createDate = LocalDateTime.now();

}
