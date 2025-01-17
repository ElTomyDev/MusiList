package com.heavydelay.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.Roles;

public interface RoleRepository extends CrudRepository<Roles, Integer>{
    Optional<Roles> findByRoleName(String roleName);
}
