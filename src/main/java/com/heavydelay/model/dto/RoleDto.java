package com.heavydelay.model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class RoleDto implements Serializable{
    private Integer idRole;
    private String roleName;
}
