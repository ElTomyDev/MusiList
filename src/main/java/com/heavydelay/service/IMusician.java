package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.musician.MusicianReturnDto;
import com.heavydelay.model.dto.musician.MusicianUpdateDto;

public interface IMusician {
    public List<MusicianReturnDto> showAllMusicians(boolean detailed);
    public MusicianReturnDto showMusicianById(Long id, boolean detailed);
    
    public MusicianReturnDto createNewMusician(MusicianUpdateDto dto);

    public void deleteMusicianById(Long id);

    public MusicianReturnDto changeMusicianAdminById(Long id, MusicianUpdateDto dto);
    public MusicianReturnDto changeMusicianRoleById(Long id, MusicianUpdateDto dto);

}
