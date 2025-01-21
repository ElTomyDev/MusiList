package com.heavydelay.model.dto.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.model.entity.Roles;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleReturnDto {
    private Integer idRole;
    private String roleName;

    // Mapeos
    public static RoleReturnDto toBasicDto(Roles role){
        return RoleReturnDto.builder()
               .idRole(role.getIdRole())
               .roleName(role.getRoleName()).build();
    }

    public static RoleReturnDto toNameDto(Roles role){
        return RoleReturnDto.builder()
               .roleName(role.getRoleName()).build();
    }
}
