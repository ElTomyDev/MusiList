package com.heavydelay.model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class GenderDto implements Serializable{
    
    private Integer idGender;
    private String genderName;

}
