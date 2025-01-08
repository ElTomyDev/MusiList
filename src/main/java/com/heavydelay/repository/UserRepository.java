package com.heavydelay.repository;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
