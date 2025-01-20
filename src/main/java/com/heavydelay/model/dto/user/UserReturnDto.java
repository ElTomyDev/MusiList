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
        return UserReturnDto.builder()
               .idUser(user.getIdUser())
               .roleName(user.getRole().getRoleName())
               .name(user.getName())
               .lastname(user.getLastname())
               .username(user.getUsername())
               .description(user.getDescription())
               .status(user.getStatus())
               .lastConnection(user.getLastConnection())
               .createDate(user.getCreateDate()).build();
    }

    public static UserReturnDto toDetailedDto(User user){
        return UserReturnDto.builder()
               .idUser(user.getIdUser())
               .role(user.getRole())
               .name(user.getName())
               .lastname(user.getLastname())
               .username(user.getUsername())
               .description(user.getDescription())
               .status(user.getStatus())
               .lastConnection(user.getLastConnection())
               .createDate(user.getCreateDate()).build();
    }

    public static UserReturnDto toSensitiveInformationDto(User user){
        return UserReturnDto.builder()
               .idUser(user.getIdUser())
               .password(user.getPassword())
               .email(user.getEmail()).build();
    }

    public static UserReturnDto toPasswordDto(User user){
        return UserReturnDto.builder()
               .idUser(user.getIdUser())
               .password(user.getPassword()).build();
    }

    public static UserReturnDto toEmailDto(User user){
        return UserReturnDto.builder()
               .idUser(user.getIdUser())
               .email(user.getEmail()).build();
    }

    public static UserReturnDto toAllDataDto(User user){
        return UserReturnDto.builder()
               .idUser(user.getIdUser())
               .role(user.getRole())
               .name(user.getName())
               .lastname(user.getLastname())
               .username(user.getUsername())
               .description(user.getDescription())
               .email(user.getEmail())
               .status(user.getStatus())
               .password(user.getPassword())
               .lastConnection(user.getLastConnection())
               .createDate(user.getCreateDate()).build();
    }
}
