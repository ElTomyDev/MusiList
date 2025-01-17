package com.heavydelay.model.dto.user;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.enums.UserStatus;
import com.heavydelay.model.entity.Roles;
import com.heavydelay.model.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserReturnDto{

    private Long idUser;

    private Roles role;
    private String roleName;

    private String name;

    private String lastname;

    private String username;

    private String email;

    private String description;

    private String password;

    private UserStatus status;

    private LocalDateTime lastConnection;
    
    private LocalDateTime createDate;

    public static UserReturnDto toBasicDto(User user){
        return new UserReturnDto(
            user.getIdUser(), 
            null,
            user.getRole().getRoleName(), 
            user.getName(), 
            user.getLastname(), 
            user.getUsername(), 
            null, 
            user.getDescription(), 
            null, 
            user.getStatus(), 
            user.getLastConnection(), 
            user.getCreateDate()
        );
    }

    public static UserReturnDto toDetailedDto(User user){
        return new UserReturnDto(
            user.getIdUser(), 
            user.getRole(),
            null, 
            user.getName(), 
            user.getLastname(), 
            user.getUsername(), 
            null, 
            user.getDescription(), 
            null, 
            user.getStatus(), 
            user.getLastConnection(), 
            user.getCreateDate()
        );
    }

    public static UserReturnDto toSensitiveInformationDto(User user){
        return new UserReturnDto(
            null, 
            null,
            null,
            null,
            null,
            null,
            user.getEmail(), 
            null,
            user.getPassword(), 
            null,
            null,
            null
        );
    }

    public static UserReturnDto toPasswordDto(User user){
        return new UserReturnDto(
            null, 
            null,
            null,
            null,
            null,
            null,
            null, 
            null,
            user.getPassword(), 
            null,
            null,
            null
        );
    }

    public static UserReturnDto toEmailDto(User user){
        return new UserReturnDto(
            null, 
            null,
            null,
            null,
            null,
            null,
            user.getEmail(), 
            null,
            null, 
            null,
            null,
            null
        );
    }
}
