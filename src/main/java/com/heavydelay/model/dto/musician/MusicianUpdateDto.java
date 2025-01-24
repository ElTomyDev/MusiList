package com.heavydelay.model.dto.musician;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MusicianUpdateDto {

    public interface CreateNewMusicianView {}
    public interface ChangedAdminView {}
    public interface ChangedRoleView {}

    @JsonView(CreateNewMusicianView.class)
    @NotBlank(groups = CreateNewMusicianView.class, message = "'username' cannot be empty")
    private String username;
    
    @JsonView(CreateNewMusicianView.class)
    @NotBlank(groups = CreateNewMusicianView.class, message = "'band name' cannot be empty")
    private String band;
    
    @JsonView({CreateNewMusicianView.class, ChangedAdminView.class})
    @NotNull(groups = {CreateNewMusicianView.class, ChangedAdminView.class}, message = "The value for 'admin' must not be null")
    private boolean isAdmin;
    
    @JsonView({CreateNewMusicianView.class, ChangedRoleView.class})
    @NotBlank(groups = {CreateNewMusicianView.class, ChangedRoleView.class}, message = "'role name' cannot be empty")
    private String roleName;

}
