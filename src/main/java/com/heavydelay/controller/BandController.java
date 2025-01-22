package com.heavydelay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.heavydelay.model.dto.band.BandReturnDto;
import com.heavydelay.model.dto.band.BandUpdateDto;
import com.heavydelay.model.payload.MessageResponse;
import com.heavydelay.service.IBand;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/band")
public class BandController {
    
    @Autowired
    private IBand bandService;

    @GetMapping("/bands/{detailed}")
    public ResponseEntity<?> showAllBands(@PathVariable boolean detailed) {
        List<BandReturnDto> bands = bandService.showAllBands(detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Bands successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(bands)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/show-id/{id}/{detailed}")
    public ResponseEntity<?> showBandById(@PathVariable Long id, @PathVariable boolean detailed) {
        BandReturnDto band = bandService.showBandById(id, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Band successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }
    
    @GetMapping("/show-name/{bandName}/{detailed}")
    public ResponseEntity<?> showBandByBandName(@PathVariable String bandName, @PathVariable boolean detailed) {
        BandReturnDto band = bandService.showBandByBandName(bandName, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Band successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(band)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/access-code/{id}")
    public ResponseEntity<?> showBandAccessCodeById(@PathVariable Long id) {
        String code = bandService.showBandAccessCodeById(id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("band access code with id '" + id + "' obtained successfully")
            .status(HttpStatus.OK.value())
            .objectResponse(code)
            .build(), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteBandById(@PathVariable Long id){
        bandService.deteleBandById(id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("band successfully removed")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse("Band with id '" + id + "' removed")
            .build(), HttpStatus.NO_CONTENT
        );
    }

    @PostMapping("/create-band")
    @JsonView(BandUpdateDto.CreateNewBandView.class)
    public ResponseEntity<?> createNewBand(@RequestBody @Valid BandUpdateDto dto) {
        BandReturnDto newBand = bandService.createNewBand(dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Band created successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(newBand)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}/update-values")
    @JsonView(BandUpdateDto.ChangeAllValuesView.class)
    public ResponseEntity<?> changeAllBandValuesById(@PathVariable Long id, @RequestBody @Valid BandUpdateDto dto) {
        BandReturnDto savedBand = bandService.changeAllBandValuesById(id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Values updated successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(savedBand)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}/update-accescode")
    @JsonView(BandUpdateDto.ChangeAccessCodeView.class)
    public ResponseEntity<?> changeAccessCodeById(@PathVariable Long id, @RequestBody @Valid BandUpdateDto dto) {
        BandReturnDto savedBand = bandService.changeAccessCodeById(id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Access code updated successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(savedBand)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}/update-bandname")
    @JsonView(BandUpdateDto.ChangeBandNameView.class)
    public ResponseEntity<?> changeBandNameById(@PathVariable Long id, @RequestBody @Valid BandUpdateDto dto) {
        BandReturnDto savedBand = bandService.changeBandNameById(id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Band name updated successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(savedBand)
            .build(), HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}/update-gender")
    @JsonView(BandUpdateDto.ChangeGenderNameView.class)
    public ResponseEntity<?> changeBandGenderById(@PathVariable Long id, @RequestBody @Valid BandUpdateDto dto) {
        BandReturnDto savedBand = bandService.changeBandGenderById(id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Gender updated successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(savedBand)
            .build(), HttpStatus.CREATED
        );
    }

}
