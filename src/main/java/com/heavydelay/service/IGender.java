package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.gender.GenderReturnDto;
import com.heavydelay.model.dto.gender.GenderUpdateDto;

public interface IGender {
    List<GenderReturnDto> showAllGenders();
    GenderReturnDto showGenderById(Integer id);
    GenderReturnDto addNewGender(GenderUpdateDto newRole);
    void deleteGenderById(Integer id);
    GenderReturnDto changeGenderNameById(Integer id, GenderUpdateDto newName);
}
