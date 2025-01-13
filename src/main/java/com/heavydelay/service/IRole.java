package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.role.CreateRoleDto;
import com.heavydelay.model.dto.role.PublicRoleDto;

public interface IRole {
    List<PublicRoleDto> showAllRoles();
    PublicRoleDto showRoleById(Integer id);
    PublicRoleDto addNewRole(CreateRoleDto newRole);
    void deleteRoleById(Integer id);
}
