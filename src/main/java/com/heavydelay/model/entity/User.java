package com.heavydelay.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.heavydelay.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Column(name="description")
    private String description;

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private UserStatus status = UserStatus.ACTIVE;

    @Column(name="create_date")
    private LocalDateTime createDate = LocalDateTime.now();

}
