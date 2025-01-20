package com.heavydelay.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.Gender;

public interface GenderRepository extends CrudRepository<Gender, Integer>{
    Optional<Gender> findByGenderName(String genderName);
}
