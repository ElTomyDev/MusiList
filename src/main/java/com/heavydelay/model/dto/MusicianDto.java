package com.heavydelay.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.heavydelay.model.entity.Band;
import com.heavydelay.model.entity.Role;
import com.heavydelay.model.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MusicianDto implements Serializable{

    private Integer idMusician;
    private User idUser;
    private Band idBand;
    private Byte isAdmin;
    private Role idRole;
    private LocalDateTime joinDate = LocalDateTime.now();

}