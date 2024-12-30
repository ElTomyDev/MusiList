package com.heavydelay.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.Band;

public interface BandDao extends CrudRepository<Band, Integer>{

}
