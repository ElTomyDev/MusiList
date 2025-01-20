package com.heavydelay.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heavydelay.exception.ResourceNotFoundException;
import com.heavydelay.model.dto.role.RoleReturnDto;
import com.heavydelay.model.dto.role.RoleUpdateDto;
import com.heavydelay.model.entity.Roles;
import com.heavydelay.repository.RoleRepository;
import com.heavydelay.service.IRole;

@Service
public class RoleImplService implements IRole{

    private RoleRepository roleRepository;

    public RoleImplService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    
    @Transactional(readOnly = false)
    @Override
    public RoleReturnDto addNewRole(RoleUpdateDto newRole) {
        
        if(newRole == null || newRole.getRoleName() == null || newRole.getRoleName().trim().isEmpty()){
            throw new IllegalArgumentException("Role name cannot be empty");
        }

        Roles role = Roles.builder().roleName(newRole.getRoleName()).build();

        
        roleRepository.save(role);
        return RoleReturnDto.toBasicDto(role);
    }

    @Override
    public void deleteRoleById(Integer id) {
        Roles deleteRole = roleRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The role with ID '" + id + "not exist")
        );
        roleRepository.delete(deleteRole);
    }

    @Override
    public List<RoleReturnDto> showAllRoles() {
         List<Roles> roles = (List<Roles>) roleRepository.findAll();

        // Retorno y mapea la lista con todos los roles
        return roles.stream().map(RoleReturnDto::toBasicDto).collect(Collectors.toList());
    }

    @Override
    public RoleReturnDto showRoleById(Integer id) {
        Roles role = roleRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The role with ID '" + id + "not exist")
        );
        return RoleReturnDto.toBasicDto(role);
    }

    @Override
    public RoleReturnDto changeRoleNameById(Integer id, RoleUpdateDto roleDto){
        Roles role = roleRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The role with ID '" + id + "not exist")
        );

        role.setRoleName(roleDto.getRoleName());

        roleRepository.save(role);
        return RoleReturnDto.toBasicDto(role);
    }

}
