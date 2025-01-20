package com.heavydelay.model.dto.gender;

import com.heavydelay.model.entity.Gender;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class GenderReturnDto {

    private Integer idGender;
    private String genderName;

    public static GenderReturnDto toBasicDto(Gender gender){
        return GenderReturnDto.builder()
                .idGender(gender.getIdGender())
                .genderName(gender.getGenderName()).build();
    } 
}
