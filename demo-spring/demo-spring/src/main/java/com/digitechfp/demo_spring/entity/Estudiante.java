package com.digitechfp.demo_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //relaciona los dos elementos (el objeto con la tabla de base de datos)
@Table(name = "estudiante")
@Data //getter y setters
@AllArgsConstructor //constructores con argumentos
@NoArgsConstructor //sin argumentos
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //incrementara el ID autoincremental
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "correo", nullable = false, unique = true, length = 100)
    private String correo;

    @Column(name = "edad")
    private int edad;

    public Estudiante(String nombre, String correo, int edad) {
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }
}
