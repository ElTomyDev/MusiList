package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.RoleDto;
import com.heavydelay.model.entity.Role;

public interface IRole{
    Role save(RoleDto roleDto);
    Role findById(Integer id);
    void delete(Role role);
    boolean existsById(Integer id);
    List<Role> listAll();
}
