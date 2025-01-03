package com.heavydelay.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.Musician;

public interface MusicianDao extends CrudRepository<Musician, Integer>{

}
