package com.heavydelay.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.heavydelay.model.entity.Band;

public interface BandRepository extends CrudRepository<Band, Long>{
    Optional<Band> findByBandName(String bandName);
}
