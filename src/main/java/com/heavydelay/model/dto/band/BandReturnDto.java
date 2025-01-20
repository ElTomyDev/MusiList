package com.heavydelay.model.dto.band;

import java.time.LocalDateTime;

import com.heavydelay.model.entity.Band;
import com.heavydelay.model.entity.Gender;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class BandReturnDto {

    private Long idBand;

    private String bandName;

    private String genderName;

    private Gender gender;

    private LocalDateTime createDate;

    private String accessCode;

    public static BandReturnDto toBasicDto(Band band){
        return BandReturnDto.builder()
                .idBand(band.getIdBand())
                .bandName(band.getBandName())
                .genderName(band.getGender().getGenderName())
                .createDate(band.getCreateDate()).build();
    }

    public static BandReturnDto toDetailedDto(Band band){
        return BandReturnDto.builder()
                .idBand(band.getIdBand())
                .bandName(band.getBandName())
                .gender(band.getGender())
                .createDate(band.getCreateDate()).build();
    }

    public static BandReturnDto AccessCode(Band band){
        return BandReturnDto.builder()
                .idBand(band.getIdBand())
                .accessCode(band.getAccessCode()).build();
    }

}
