package com.heavydelay.model.entity;

import java.io.Serializable;

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
@Table(name="roles")
public class Role implements Serializable{
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    @Id
    @Column(name="id_role")
    private Integer idRole;

    @Column(name="role_name")
    private String roleName;
}
