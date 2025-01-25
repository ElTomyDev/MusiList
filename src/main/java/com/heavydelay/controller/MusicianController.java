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
import com.heavydelay.model.dto.musician.MusicianReturnDto;
import com.heavydelay.model.dto.musician.MusicianUpdateDto;
import com.heavydelay.model.payload.MessageResponse;
import com.heavydelay.service.IMusician;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/musician")
public class MusicianController {

    @Autowired
    private IMusician musicianService;

    @GetMapping("/musicians/{detailed}")
    public ResponseEntity<?> showAllMusician(@PathVariable boolean detailed) {
        List<MusicianReturnDto> musicians = musicianService.showAllMusicians(detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Musicians successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(musicians)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}/{detailed}")
    public ResponseEntity<?> showAllMusician(@PathVariable Long id, @PathVariable boolean detailed) {
        MusicianReturnDto musician = musicianService.showMusicianById(id, detailed);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Musician successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(musician)
            .build(), HttpStatus.OK
        );
    }
    
    @PostMapping("create-musician")
    @JsonView(MusicianUpdateDto.CreateNewMusicianView.class)
    public ResponseEntity<?> createNewMusician(@RequestBody @Valid MusicianUpdateDto dto) {
        MusicianReturnDto newMusician = musicianService.createNewMusician(dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Musician created successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(newMusician)
            .build(), HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteMusician(@PathVariable Long id) {
        musicianService.deleteMusicianById(id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Musician successfully removed")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse("Musician with ID '" + id + "' deleted")
            .build(), HttpStatus.NO_CONTENT
        );
    }

    @PutMapping("/{id}/update-role")
    @JsonView(MusicianUpdateDto.ChangedRoleView.class)
    public ResponseEntity<?> changeMusicianRole(@PathVariable Long id, @RequestBody @Valid MusicianUpdateDto dto) {
        MusicianReturnDto updateMusician = musicianService.changeMusicianRoleById(id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Musician role updated successfully")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(updateMusician)
            .build(), HttpStatus.NO_CONTENT
        );
    }

    @PutMapping("/{id}/update-admin")
    @JsonView(MusicianUpdateDto.ChangedAdminView.class)
    public ResponseEntity<?> changeMusicianAdmin(@PathVariable Long id, @RequestBody @Valid MusicianUpdateDto dto) {
        MusicianReturnDto updateMusician = musicianService.changeMusicianAdminById(id, dto);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Musician admin updated successfully")
            .status(HttpStatus.NO_CONTENT.value())
            .objectResponse(updateMusician)
            .build(), HttpStatus.NO_CONTENT
        );
    }
}
