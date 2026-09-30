package com.digitechfp.demo_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "profesor")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreCompleto", nullable = false, length = 50)
    private String nombreCompleto;

    @Column(name = "especialidad", length = 30)
    private String especialidad;

    @Column(name = "experienciaAnios")
    private int experienciaAnios;

    public Profesor(String nombreCompleto, String especialidad, int experienciaAnios) {
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.experienciaAnios = experienciaAnios;
    }
}
