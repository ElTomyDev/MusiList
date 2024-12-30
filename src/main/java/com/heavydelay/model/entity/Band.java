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
@Table(name="bands")
public class Band implements Serializable{
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    
    @Id
    @Column(name="id_band")
    private Integer idBand;
    @Column(name="band_name")
    private String bandName;
    @Column(name="create_date")
    private Timestamp createDate;
    @Column(name="access_code")
    private String accessCode;
}
