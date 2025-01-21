package com.heavydelay.service;

import java.util.List;

import com.heavydelay.model.dto.band.BandReturnDto;
import com.heavydelay.model.dto.band.BandUpdateDto;

public interface IBand {
    public List<BandReturnDto> showAllBands(boolean detailed);
    public BandReturnDto showBandById(Long id, boolean detailed);
    public BandReturnDto showBandByBandName(String bandName, boolean detailed);
    public String showBandAccessCodeById(Long id);

    public void deteleBandById(Long id);

    public BandReturnDto createNewBand(BandUpdateDto dto);

    public BandReturnDto changeAllBandValuesById(Long id, BandUpdateDto dto);
    public BandReturnDto changeBandNameById(Long id, BandUpdateDto dto);
    public BandReturnDto changeBandGenderById(Long id, BandUpdateDto dto);
    public BandReturnDto changeAccessCodeById(Long id, BandUpdateDto dto);
}
