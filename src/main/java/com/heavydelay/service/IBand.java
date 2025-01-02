package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.BandDto;
import com.heavydelay.model.entity.Band;

public interface IBand {
    Band save(BandDto bandDto);
    Band findById(Integer id);
    void delete(Band band);
    boolean existsById(Integer id);
    List<Band> listAll();
}
