package com.heavydelay.model.dto.band;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BandUpdateDto {

    public interface CreateNewBand {}
    public interface ChangeAccessCode {}
    public interface ChangeAllValues {}

    @JsonView({CreateNewBand.class, ChangeAllValues.class})
    @NotBlank(groups = CreateNewBand.class, message = "'band name' cannot be empty")
    @Size(groups = CreateNewBand.class, max = 125, message = "The 'band name' cannot exceed 125 characters.")
    private String bandName;

    @JsonView({CreateNewBand.class, ChangeAllValues.class})
    @NotBlank(groups = CreateNewBand.class, message = "'gender name' cannot be empty")
    private String genderName;
    
    @JsonView({ChangeAccessCode.class, ChangeAllValues.class})
    @NotBlank(groups = ChangeAccessCode.class, message = "'Access code' cannot be empty")
    @Size(groups = ChangeAccessCode.class, max = 20, message = "The 'access code' cannot exceed 20 characters.")
    private String accessCode;
    
}
