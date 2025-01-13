package com.heavydelay.model.dto.role;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class PublicRoleDto {
    private Integer idRole;
    private String roleName;
}
