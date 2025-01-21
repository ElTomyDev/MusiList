package com.heavydelay.service.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.heavydelay.exception.ResourceNotFoundException;
import com.heavydelay.model.dto.band.BandReturnDto;
import com.heavydelay.model.dto.band.BandUpdateDto;
import com.heavydelay.model.entity.Band;
import com.heavydelay.repository.BandRepository;
import com.heavydelay.repository.GenderRepository;
import com.heavydelay.service.IBand;
import com.heavydelay.util.AccessCodeGenerator;

public class BandImplService implements IBand{

    private BandRepository bandRepository;
    private GenderRepository genderRepository;

    public BandImplService(BandRepository bandRepository, GenderRepository genderRepository){
        this.bandRepository = bandRepository;
        this.genderRepository = genderRepository;
    }

    @Override
    public BandReturnDto changeAccessCodeById(Long id, BandUpdateDto dto) {
        Band band = bandRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The band with ID '" + id + "' not found")
        );

        band.setAccessCode(dto.getAccessCode());

        bandRepository.save(band);
        return BandReturnDto.toDetailedDto(band);
    }

    @Override
    public BandReturnDto changeAllBandValuesById(Long id, BandUpdateDto dto) {
        Band band = bandRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The band with ID '" + id + "' not found")
        );

        band.setBandName(dto.getBandName());
        band.setGender(genderRepository.findByGenderName(
            dto.getBandName()).orElseThrow(
                () -> new ResourceNotFoundException("The gender with name '" + dto.getGenderName() + "' not found")
            )
        );
        band.setAccessCode(dto.getAccessCode());
        
        bandRepository.save(band);
        return BandReturnDto.toDetailedDto(band);
    }

    @Override
    public BandReturnDto changeBandGenderById(Long id, BandUpdateDto dto) {
        Band band = bandRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The band with ID '" + id + "' not found")
        );

        band.setGender(genderRepository.findByGenderName(
            dto.getBandName()).orElseThrow(
                () -> new ResourceNotFoundException("The gender with name '" + dto.getGenderName() + "' not found")
            )
        );

        bandRepository.save(band);
        return BandReturnDto.toDetailedDto(band);
    }

    @Override
    public BandReturnDto changeBandNameById(Long id, BandUpdateDto dto) {
        Band band = bandRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The band with ID '" + id + "' not found")
        );

        band.setBandName(dto.getBandName());

        bandRepository.save(band);
        return BandReturnDto.toDetailedDto(band);
    }

    @Override
    public BandReturnDto createNewBand(BandUpdateDto dto) {
        Band newBand = Band.builder()
                       .bandName(dto.getBandName())
                       .accessCode(AccessCodeGenerator.generateAccessCode(6))
                       .build();
        
        // le asigno el genero de la nueva banda
        newBand.setGender(genderRepository.findByGenderName(
            dto.getGenderName()).orElseThrow(
                () -> new ResourceNotFoundException("The gender with name '" + dto.getGenderName() + "' not found")
            )
        );

        bandRepository.save(newBand);
        return BandReturnDto.toDetailedDto(newBand);
    }
    
    @Override
    public void deteleBandById(Long id) {
        Band band = bandRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The band with ID '" + id + "' not found")
        );
        bandRepository.delete(band);
        
    }

    @Override
    public List<BandReturnDto> showAllBands(boolean detailed) {
        List<Band> bands = (List<Band>) bandRepository.findAll();

        // Selección del DTO a usar 
        Function<Band, BandReturnDto> mapper = detailed ? BandReturnDto::toDetailedDto : BandReturnDto::toBasicDto;

        // Retorno y mapea la lista con todas las bandas
        return bands.stream().map(mapper).collect(Collectors.toList());
    }

    @Override
    public BandReturnDto showBandByBandName(String bandName, boolean detailed) {
        Band band = bandRepository.findByBandName(bandName).orElseThrow(
            () -> new ResourceNotFoundException("The band with name '" + bandName + "' not found")
        );
        return detailed ? BandReturnDto.toDetailedDto(band) : BandReturnDto.toBasicDto(band);
    }

    @Override
    public BandReturnDto showBandById(Long id, boolean detailed) {
        Band band = bandRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The band with ID '" + id + "' not found")
        );
        return detailed ? BandReturnDto.toDetailedDto(band) : BandReturnDto.toBasicDto(band);
    }

    @Override
    public BandReturnDto showBandAccessCodeById(Long id) {
        Band band = bandRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("The band with ID '" + id + "' not found")
        );
        return BandReturnDto.AccessCode(band);
    }
}
