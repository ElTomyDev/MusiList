package com.heavydelay.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.heavydelay.exception.ResourceNotFoundException;
import com.heavydelay.model.dto.gender.GenderReturnDto;
import com.heavydelay.model.dto.gender.GenderUpdateDto;
import com.heavydelay.model.entity.Gender;
import com.heavydelay.repository.GenderRepository;
import com.heavydelay.service.IGender;

@Service
public class GenderImplService implements IGender{

    private GenderRepository genderRepository;

    public GenderImplService(GenderRepository genderRepository){
        this.genderRepository = genderRepository;
    }

    @Override
    public GenderReturnDto addNewGender(GenderUpdateDto newGender) {
        if(newGender == null || newGender.getGenderName() == null || newGender.getGenderName().trim().isEmpty()){
            throw new IllegalArgumentException("Gender name cannot be empty");
        }

        Gender gender = Gender.builder().genderName(newGender.getGenderName()).build();

        
        genderRepository.save(gender);
        return GenderReturnDto.toBasicDto(gender);
    }

    @Override
    public GenderReturnDto changeGenderNameById(Integer id, GenderUpdateDto genderDto) {
        Gender gender = genderRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The gender with ID '" + id + "not exist")
        );

        gender.setGenderName(genderDto.getGenderName());

        genderRepository.save(gender);
        return GenderReturnDto.toBasicDto(gender);
    }

    @Override
    public void deleteGenderById(Integer id) {
        Gender deleteGender = genderRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The gender with ID '" + id + "not exist")
        );
        genderRepository.delete(deleteGender);
        
    }

    @Override
    public List<GenderReturnDto> showAllGenders() {
        List<Gender> gender = (List<Gender>) genderRepository.findAll();

        // Retorno y mapea la lista con todos los roles
        return gender.stream().map(GenderReturnDto::toBasicDto).collect(Collectors.toList());
    }

    @Override
    public GenderReturnDto showGenderById(Integer id) {
        Gender gender = genderRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The gender with ID '" + id + "not exist")
        );
        return GenderReturnDto.toBasicDto(gender);
    }

}
