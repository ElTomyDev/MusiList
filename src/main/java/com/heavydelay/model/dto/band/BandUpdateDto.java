package com.heavydelay.model.dto.band;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BandUpdateDto {

    public interface CreateNewBandView {}
    public interface ChangeAccessCodeView {}
    public interface ChangeBandNameView {}
    public interface ChangeGenderNameView {}
    public interface ChangeAllValuesView {}

    @JsonView({CreateNewBandView.class, ChangeAllValuesView.class, ChangeBandNameView.class})
    @NotBlank(groups = {CreateNewBandView.class, ChangeAllValuesView.class, ChangeBandNameView.class}, message = "'band name' cannot be empty")
    @Size(groups = {CreateNewBandView.class, ChangeAllValuesView.class, ChangeBandNameView.class}, max = 125, message = "The 'band name' cannot exceed 125 characters.")
    private String bandName;

    @JsonView({CreateNewBandView.class, ChangeAllValuesView.class, ChangeGenderNameView.class})
    @NotBlank(groups = {CreateNewBandView.class, ChangeAllValuesView.class, ChangeGenderNameView.class}, message = "'gender name' cannot be empty")
    private String genderName;
    
    @JsonView({ChangeAccessCodeView.class, ChangeAllValuesView.class})
    @NotBlank(groups = {ChangeAccessCodeView.class, ChangeAllValuesView.class}, message = "'Access code' cannot be empty")
    @Size(groups = {ChangeAccessCodeView.class, ChangeAllValuesView.class}, max = 6, message = "The 'access code' cannot exceed 6 characters.")
    private String accessCode;
    
}
