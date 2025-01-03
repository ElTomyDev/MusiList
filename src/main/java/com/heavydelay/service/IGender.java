package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.GenderDto;
import com.heavydelay.model.entity.Gender;

public interface IGender {
    Gender save(GenderDto genderDto);
    Gender findById(Integer id);
    void delete(Gender gender);
    boolean existsById(Integer id);
    List<Gender> listAll();
}
