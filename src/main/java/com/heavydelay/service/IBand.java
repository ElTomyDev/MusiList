package com.heavydelay.service;

import com.heavydelay.model.dto.band.BandReturnDto;
import com.heavydelay.model.dto.band.BandUpdateDto;

public interface IBand {
    public BandReturnDto showAllBands(boolean detailed);
    public BandReturnDto showBandById(Long id, boolean detailed);
    public BandReturnDto showBandByBandName(boolean detailed);

    public void deteleBandById(Long id);

    public BandReturnDto createNewBand(BandUpdateDto dto);

    public BandReturnDto changeAllBandValuesById(Long id, BandUpdateDto dto);
    public BandReturnDto changeBandNameById(Long id, BandUpdateDto dto);
    public BandReturnDto changeBandGenderById(Long id, BandUpdateDto dto);
    public BandReturnDto changeAccessCodeById(Long id, BandUpdateDto dto);
}
