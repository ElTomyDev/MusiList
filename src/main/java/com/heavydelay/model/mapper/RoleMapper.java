package com.heavydelay.model.mapper;

import org.mapstruct.Mapper;

import com.heavydelay.model.dto.role.PublicRoleDto;
import com.heavydelay.model.entity.Roles;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    PublicRoleDto toDto(Roles role);
    Roles toEntity(PublicRoleDto dto);
}
