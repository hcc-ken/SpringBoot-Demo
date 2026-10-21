package com.digitechfp.demo_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "oficina_id", referencedColumnName = "id") // Establecemos aqui en nombre de la ForeignKey
    private Oficina oficina;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Asignatura> asignaturas = new ArrayList<>();

    public Profesor(String nombreCompleto, String especialidad, int experienciaAnios, Oficina oficina) {
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.experienciaAnios = experienciaAnios;
        this.oficina = oficina;
    }

    public void agregarAsignaturas(Asignatura asignatura) {
        asignaturas.add(asignatura);
        asignatura.setProfesor(this);
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
        if (asignaturas != null) {
            for (Asignatura asignatura : asignaturas) {
                asignatura.setProfesor(this);

            }
        }
    }
}
