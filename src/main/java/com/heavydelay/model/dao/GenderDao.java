package com.heavydelay.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.Gender;

public interface GenderDao extends CrudRepository<Gender, Integer>{

}
