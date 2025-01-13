package com.heavydelay.repository;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.Roles;

public interface RoleRepository extends CrudRepository<Roles, Integer>{

}
