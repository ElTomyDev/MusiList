package com.heavydelay.model.dto.musician;

import com.fasterxml.jackson.annotation.JsonView;
import com.heavydelay.model.entity.Band;
import com.heavydelay.model.entity.User;

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
    @NotNull(groups = CreateNewMusicianView.class, message = "The value for 'idUser' must not be null")
    private Long idUser;
    
    @JsonView(CreateNewMusicianView.class)
    @NotNull(groups = CreateNewMusicianView.class, message = "The value for 'idBand' must not be null")
    private Long idBand;
    
    @JsonView({CreateNewMusicianView.class, ChangedAdminView.class})
    @NotNull(groups = {CreateNewMusicianView.class, ChangedAdminView.class}, message = "The value for 'admin' must not be null")
    private boolean isAdmin;
    
    @JsonView({CreateNewMusicianView.class, ChangedRoleView.class})
    @NotBlank(groups = {CreateNewMusicianView.class, ChangedRoleView.class}, message = "'role name' cannot be empty")
    private String roleName;

}
