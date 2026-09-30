package com.digitechfp.demo_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "administrador")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombreCompleto", nullable = false, length = 50)
    private Long nombreCompleto;

    @Column(name = "departamento", length = 30)
    private String departamento;

    @Column(name = "anioInicial")
    private int anioInicial;

    public Administrador(Long nombreCompleto, String departamento, int anioInicial) {
        this.nombreCompleto = nombreCompleto;
        this.departamento = departamento;
        this.anioInicial = anioInicial;
    }
}
