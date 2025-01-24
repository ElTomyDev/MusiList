package com.heavydelay.repository;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.Musician;

public interface MusicianRepository extends CrudRepository<Musician, Long>{

}
