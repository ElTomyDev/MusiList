package com.heavydelay.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.Gender;
import com.heavydelay.model.entity.Roles;

public interface GenderRepository extends CrudRepository<Roles, Integer>{
    Optional<Gender> findByGenderName(String genderName);
}
