package com.heavydelay.model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class UserDto implements Serializable{

    private Integer idUser;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Timestamp createDate;
    private boolean isAdmin;
    private Integer idRole;
    private Integer idBand;

    public boolean getIsAdmin(){
        return this.isAdmin;
    }

    public void getIsAdmin(boolean admin){
        this.isAdmin = admin;
    }
}
