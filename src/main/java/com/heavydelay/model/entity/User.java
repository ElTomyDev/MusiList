package com.heavydelay.model.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="users")
public class User implements Serializable{
    @GeneratedValue(strategy=GenerationType.IDENTITY)

    @Id
    @Column(name="id_user")
    private Integer idUser;
    
    @Column(name="name")
    private String name;

    @Column(name="lastname")
    private String lastname;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="create_date")
    private Timestamp createDate;

    @Column(name="is_admin")
    private boolean isAdmin;

    @Column(name="id_role")
    private Integer idRole;

    @Column(name="id_band")
    private Integer idBand;
    
    public boolean getIsAdmin(){
        return this.isAdmin;
    }

    public void getIsAdmin(boolean admin){
        this.isAdmin = admin;
    }
}
