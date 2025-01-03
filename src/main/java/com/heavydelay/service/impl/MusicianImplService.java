package com.heavydelay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavydelay.model.dao.MusicianDao;
import com.heavydelay.model.dto.MusicianDto;
import com.heavydelay.model.entity.Musician;
import com.heavydelay.service.IMusician;

@Service
public class MusicianImplService implements IMusician{

    @Autowired
    private MusicianDao musicianDao;

    @Override
    public void delete(Musician musician) {
        musicianDao.delete(musician);        
    }

    @Override
    public boolean existsById(Integer id) {
        return musicianDao.existsById(id);
    }

    @Override
    public Musician findById(Integer id) {
        return musicianDao.findById(id).orElse(null);
    }

    @Override
    public List<Musician> listAll() {
        return (List<Musician>) musicianDao.findAll();
    }

    @Override
    public Musician save(MusicianDto musicianDto) {
        Musician musician = Musician.builder()
                            .idMusician(musicianDto.getIdMusician())
                            .idUser(musicianDto.getIdUser())
                            .idBand(musicianDto.getIdBand())
                            .isAdmin(musicianDto.getIsAdmin())
                            .idRole(musicianDto.getIdRole())
                            .joinDate(musicianDto.getJoinDate())
                            .build();
        return musicianDao.save(musician);
    }
    
    
}
