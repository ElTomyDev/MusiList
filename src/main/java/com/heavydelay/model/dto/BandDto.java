package com.heavydelay.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.heavydelay.model.entity.Gender;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class BandDto implements Serializable{
    private Integer idBand;
    private String bandName;
    private Gender idGender;
    private LocalDateTime createDate;
    private String accessCode;
}
