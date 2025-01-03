package com.heavydelay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavydelay.model.dao.GenderDao;
import com.heavydelay.model.dto.GenderDto;
import com.heavydelay.model.entity.Gender;
import com.heavydelay.service.IGender;

@Service
public class GenderImplService implements IGender{
    
    @Autowired
    private GenderDao genderDao;

    @Override
    public void delete(Gender gender) {
        genderDao.delete(gender);
    }

    @Override
    public boolean existsById(Integer id) {
        return genderDao.existsById(id);
    }

    @Override
    public Gender findById(Integer id) {
        return genderDao.findById(id).orElse(null);
    }

    @Override
    public List<Gender> listAll() {
        return (List) genderDao.findAll();
    }

    @Override
    public Gender save(GenderDto genderDto) {
        Gender gender = Gender.builder()
                        .idGender(genderDto.getIdGender())
                        .genderName(genderDto.getGenderName())
                        .build();
        return genderDao.save(gender);
    }

    
}
