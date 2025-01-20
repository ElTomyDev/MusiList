package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.role.RoleUpdateDto;
import com.heavydelay.model.dto.role.RoleReturnDto;

public interface IRole {
    List<RoleReturnDto> showAllRoles();
    RoleReturnDto showRoleById(Integer id);
    RoleReturnDto addNewRole(RoleUpdateDto newRole);
    void deleteRoleById(Integer id);
    RoleReturnDto changeRoleNameById(Integer id, RoleUpdateDto newName);
}
