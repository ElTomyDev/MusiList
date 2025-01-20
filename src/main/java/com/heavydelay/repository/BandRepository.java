package com.heavydelay.repository;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.Band;

public interface BandRepository extends CrudRepository<Band, Long>{

}
