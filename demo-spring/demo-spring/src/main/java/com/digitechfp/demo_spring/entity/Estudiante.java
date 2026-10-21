package com.digitechfp.demo_spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil_id", referencedColumnName = "id") //Establecemos aqui en nombre de la ForeignKey
    private PerfilAcademico perfilAcademico;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<ContactoAuxiliar> contactos = new ArrayList<>();

    public Estudiante(String nombre, String correo, int edad, PerfilAcademico perfilAcademico) {
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
        this.perfilAcademico = perfilAcademico;
    }

    public void agregarContacto(ContactoAuxiliar contactoAuxiliar) {
        contactos.add(contactoAuxiliar);
        contactoAuxiliar.setEstudiante(this);
    }

    public void setContactos(List<ContactoAuxiliar> contactos) {
        this.contactos = contactos;
        if (contactos != null) {
            for (ContactoAuxiliar contactoAuxiliar : contactos) {
                contactoAuxiliar.setEstudiante(this);
            }
        }
    }
}
