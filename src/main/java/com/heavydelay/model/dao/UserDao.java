package com.heavydelay.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.User;

public interface UserDao extends CrudRepository<User, Integer>{

}
