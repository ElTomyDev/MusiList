package com.heavydelay.model.entity;

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
@Table(name="bands")
public class Band {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_band")
    private Long idBand;

    @Column(name="band_name")
    private String bandName;

    @ManyToOne
    @JoinColumn(name = "id_gender")
    private Gender gender;

    @Column(name="create_date")
    @Builder.Default
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name="access_code")
    private String accessCode;

}
