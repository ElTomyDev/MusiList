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

import com.heavydelay.model.dto.gender.GenderReturnDto;
import com.heavydelay.model.dto.gender.GenderUpdateDto;
import com.heavydelay.model.payload.MessageResponse;
import com.heavydelay.service.IGender;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/gender")
public class GenderController {

    @Autowired
    private IGender genderService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewGender(@RequestBody @Valid GenderUpdateDto newGender){
        GenderReturnDto gender = genderService.addNewGender(newGender);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Gender created successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(gender)
            .build(), HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deleteGenderById(@PathVariable Integer id){
        genderService.deleteGenderById(id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Gender deleted successfully")
            .status(HttpStatus.OK.value())
            .objectResponse("Gender with ID '" + id + "'' delete")
            .build(), HttpStatus.OK
        );
    }

    @PutMapping("{id}/update-name")
    public ResponseEntity<?> changeGenderNameById(@PathVariable Integer id, @RequestBody @Valid GenderUpdateDto newGenderName) {
        GenderReturnDto gender = genderService.changeGenderNameById(id, newGenderName);
        
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Gender name updated successfully")
            .status(HttpStatus.CREATED.value())
            .objectResponse(gender)
            .build(), HttpStatus.CREATED
        );
    }

    @GetMapping("/genders")
    public ResponseEntity<?> showAllGenders() {
        List<GenderReturnDto> gender = genderService.showAllGenders();
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("All genders obtained correctly")
            .status(HttpStatus.OK.value())
            .objectResponse(gender)
            .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showGenderById(@PathVariable Integer id) {
        GenderReturnDto gender = genderService.showGenderById(id);
        return new ResponseEntity<>(
            MessageResponse.builder()
            .message("Gender successfully obtained")
            .status(HttpStatus.OK.value())
            .objectResponse(gender)
            .build(), HttpStatus.OK
        );
    }
}
