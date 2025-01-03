package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.MusicianDto;
import com.heavydelay.model.entity.Musician;

public interface IMusician {
    Musician save(MusicianDto musicianDto);
    Musician findById(Integer id);
    void delete(Musician musician);
    boolean existsById(Integer id);
    List<Musician> listAll();
}
