package com.heavydelay.model.dto.gender;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class GenderUpdateDto {

    @NotBlank(message = "The gender name must not be empty")
    private String genderName;
}
