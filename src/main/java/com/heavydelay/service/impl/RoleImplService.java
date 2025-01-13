package com.heavydelay.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heavydelay.exception.ResourceNotFoundException;
import com.heavydelay.model.dto.role.CreateRoleDto;
import com.heavydelay.model.dto.role.PublicRoleDto;
import com.heavydelay.model.entity.Roles;
import com.heavydelay.model.mapper.RoleMapper;
import com.heavydelay.repository.RoleRepository;
import com.heavydelay.service.IRole;

@Service
public class RoleImplService implements IRole{

    private RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleImplService(RoleRepository roleRepository, RoleMapper roleMapper){
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }
    
    @Transactional(readOnly = false)
    @Override
    public PublicRoleDto addNewRole(CreateRoleDto newRole) {
        Roles role = Roles.builder().roleName(newRole.getRoleName()).build();
        
        roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    public void deleteRoleById(Integer id) {
        Roles deleteRole = roleRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The role with ID '" + id + "not exist")
        );
        roleRepository.delete(deleteRole);
    }

    @Override
    public List<PublicRoleDto> showAllRoles() {
         List<Roles> roles = (List<Roles>) roleRepository.findAll();

        // Retorno y mapea la lista con todos los roles
        return roles.stream().map(roleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public PublicRoleDto showRoleById(Integer id) {
        Roles role = roleRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The role with ID '" + id + "not exist")
        );
        return roleMapper.toDto(role);
    }

}
