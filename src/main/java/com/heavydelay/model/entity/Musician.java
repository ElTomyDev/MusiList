package com.heavydelay.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="musicians")
public class Musician implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_musician")
    private Integer idMusician;

    @ManyToOne
    @JoinColumn(name="id_user")
    private User idUser;

    @ManyToOne
    @JoinColumn(name="id_band")
    private Band idBand;

    @Column(name="is_admin")
    private Byte isAdmin;

    @ManyToOne
    @JoinColumn(name="id_role")
    private Role idRole;

    @Column(name="join_date")
    private LocalDateTime joinDate = LocalDateTime.now();

}
