package com.heavydelay.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class BandDto implements Serializable{
    private Integer idBand;
    private String bandName;
    private Integer idGender;
    private LocalDateTime createDate = LocalDateTime.now();
    private String accessCode;
}
