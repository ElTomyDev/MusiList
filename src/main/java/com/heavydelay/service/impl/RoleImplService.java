package com.heavydelay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heavydelay.model.dao.RoleDao;
import com.heavydelay.model.dto.RoleDto;
import com.heavydelay.model.entity.Role;
import com.heavydelay.service.IRole;

@Service
public class RoleImplService implements IRole{

    @Autowired
    private RoleDao roleDao;

    @Transactional(readOnly=true)
    @Override
    public void delete(Role role) {
        roleDao.delete(role);
    }

    @Override
    public boolean existsById(Integer id) {
        return roleDao.existsById(id);
    }

    @Override
    public Role findById(Integer id) {
        return roleDao.findById(id).orElse(null);
    }

    @Override
    public List<Role> listAll() {
        return (List) roleDao.findAll();
    }

    @Override
    public Role save(RoleDto roleDto) {
        Role role = Role.builder()
                    .idRole(roleDto.getIdRole())
                    .roleName(roleDto.getRoleName())
                    .build();
        return roleDao.save(role);
    }
    
}
