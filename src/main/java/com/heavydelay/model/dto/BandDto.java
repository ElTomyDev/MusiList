package com.heavydelay.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class BandDto implements Serializable{
    private Integer idBand;
    private String bandName;
    private Timestamp createDate;
    private String accessCode;
}
