package com.heavydelay.model.dto.musician;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heavydelay.model.entity.Band;
import com.heavydelay.model.entity.Musician;
import com.heavydelay.model.entity.Roles;
import com.heavydelay.model.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MusicianReturnDto {
    
    private Long idMusician;

    private User user;
    private String username;

    private Band band;
    private String bandName;

    private boolean isAdmin;

    private Roles role;
    private String roleName;

    private LocalDateTime joinDate;

    public MusicianReturnDto toBasicDto(Musician musician){
        return MusicianReturnDto.builder()
               .idMusician(musician.getIdMusician())
               .username(musician.getUser().getUsername())
               .bandName(musician.getBand().getBandName())
               .isAdmin(musician.isAdmin())
               .roleName(musician.getRole().getRoleName())
               .joinDate(musician.getJoinDate()).build();
    }

    public MusicianReturnDto toDetailedDto(Musician musician){
        return MusicianReturnDto.builder()
               .idMusician(musician.getIdMusician())
               .user(musician.getUser())
               .band(musician.getBand())
               .isAdmin(musician.isAdmin())
               .role(musician.getRole())
               .joinDate(musician.getJoinDate()).build();
    }
}
