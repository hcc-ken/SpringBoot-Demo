package com.digitechfp.demo_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "oficina")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Oficina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigoOficina", length = 250)
    private String codigoOficina;

    @Column(name = "edificio", length = 20)
    private String edificio;

    public Oficina(String codigoOficina, String edificio) {
        this.codigoOficina = codigoOficina;
        this.edificio = edificio;
    }
}
