package com.heavydelay.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.heavydelay.model.dao.BandDao;
import com.heavydelay.model.dto.BandDto;
import com.heavydelay.model.entity.Band;
import com.heavydelay.service.IBand;

public class BandImplService implements IBand{

    @Autowired
    private BandDao bandDao;


    @Transactional(readOnly=true)
    @Override
    public void delete(Band band) {
        bandDao.delete(band);        
    }

    @Override
    public boolean existsById(Integer id) {
        return bandDao.existsById(id);
    }

    @Override
    public Band findById(Integer id) {
        return bandDao.findById(id).orElse(null);
    }

    @Override
    public List<Band> listAll() {
        return (List) bandDao.findAll();
    }

    @Override
    public Band save(BandDto bandDto) {
        Band band = Band.builder()
                    .idBand(bandDto.getIdBand())
                    .bandName(bandDto.getBandName())
                    .createDate(bandDto.getCreateDate())
                    .accessCode(bandDto.getAccessCode())
                    .build();
        return bandDao.save(band);
    }

    
}
