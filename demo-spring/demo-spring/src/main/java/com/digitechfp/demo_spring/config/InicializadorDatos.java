package com.digitechfp.demo_spring.config;

import com.digitechfp.demo_spring.entity.Estudiante;
import com.digitechfp.demo_spring.entity.Profesor;
import com.digitechfp.demo_spring.repository.EstudianteRepository;
import com.digitechfp.demo_spring.repository.ProfesorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class InicializadorDatos {
    @Bean
    CommandLineRunner initData(EstudianteRepository estudianteRepository){
        return args -> {
            // INSERTAR ESTUDIANTES
            Estudiante estudiante1 = new Estudiante("Wilman Martins", "wm@digitechfp.com", 20);
            Estudiante estudiante2 = new Estudiante("Ken Pendejo", "KP@digitechfp.com", 23);

            estudianteRepository.save(estudiante1);
            estudianteRepository.save(estudiante2);

            System.out.println(">>>> Estudiantes guardados correctamente en la BBDD");

            System.out.println(">>>> Listando estudiantes guardados en la BBDD");
            for (Estudiante e: estudianteRepository.findAll()) {
                System.out.println("- ID: " + e.getId() + ", Nombre: " + e.getNombre() + ", Correo: " + e.getCorreo() + ", Edad: " + e.getEdad());
            }

            // CONSULTAR UN ESTUDIANTE SOLAMENTE
            System.out.println(">>>> Consultando un estudiante por ID");
            Optional<Estudiante> e = estudianteRepository.findById(1L);
            e.ifPresent(estudiante -> System.out.println("- ID: " + estudiante.getId() + ", Nombre: " + estudiante.getNombre() + ", Correo: " + estudiante.getCorreo() + ", Edad: " + estudiante.getEdad()));

            // MODIFICAR DATOS
            System.out.println("\n>>>> Actualizar ID ===");
            Estudiante estudianteEncontrado = estudianteRepository.findById(2L).orElseThrow();

            estudianteEncontrado.setNombre("David María Jalapoyas");
            estudianteEncontrado.setCorreo("david.maria.jalapoyas@digitechfp.com");
            estudianteEncontrado.setEdad(20);

            estudianteRepository.save(estudianteEncontrado);

            System.out.println(">>>> El resultado es:");
            System.out.println("Actualizado: " + estudianteRepository.findById(2L).orElse(null));

            // CONTAR LOS REGISTROS DE ESTUDIANTES
            System.out.println(">>>> Contamos los estudiantes");
            System.out.println("El total de estudiantes es: " + estudianteRepository.count());

            // ELIMINAR UN ESTUDIANTE
            System.out.println(">>>> Eliminamos un estudiante");
            estudianteRepository.deleteById(2L);
            System.out.println(">>>> Estudiante eliminado correctamente");
            for (Estudiante est : estudianteRepository.findAll()) {
                System.out.println("- ID: " + est.getId() + ", Nombre: " + est.getNombre() + ", Correo: " + est.getCorreo() + ", Edad: " + est.getEdad());
            }
        };
    }

    @Bean
    CommandLineRunner initDataP(ProfesorRepository profesorRepository) {
        return args -> {
            // INSERTAR PROFESORES
            System.out.println(">>>> Insertando profesores");
            Profesor profesor1 = new Profesor("Juan Pérez", "Base de datos", 15);
            Profesor profesor2 = new Profesor("María López", "Programación", 8);
            Profesor profesor3 = new Profesor("Carlos García", "Redes", 10);

            profesorRepository.save(profesor1);
            profesorRepository.save(profesor2);
            profesorRepository.save(profesor3);

            System.out.println(">>>> Profesores guardados correctamente en la BBDD");

            // MODIFICANDO PROFESORES

            System.out.println(">>>> Modificando datos");
            Profesor profesorEncontrado = profesorRepository.findById(1L).orElseThrow();
            profesorEncontrado.setNombreCompleto("Roberto Carlos Pérez");
            profesorEncontrado.setEspecialidad("Inteligencia Artificial");
            profesorEncontrado.setExperienciaAnios(20);

            profesorRepository.save(profesorEncontrado);

            System.out.println(">>>> Listando profesores guardados en la BBDD");
            System.out.println("Actualizado: " + profesorRepository.findById(1L).orElse(null));

            // BORRANDO PROFESORES

            System.out.println(">>>> Borrando un profesor");
            profesorRepository.deleteById(2L);
            System.out.println(">>>> Profesor eliminado correctamente");
            for (Profesor prof : profesorRepository.findAll()) {
                System.out.println("- ID: " + prof.getId() + ", Nombre: " + prof.getNombreCompleto() + ", Especialidad: " + prof.getEspecialidad() + ", Experiencia: " + prof.getExperienciaAnios() + " años");
            }

            // MOSTRANDO PROFESORES RESTANTES

            System.out.println(">>>> Listando profesores restantes en la BBDD");
            for (Profesor prof : profesorRepository.findAll()) {
                System.out.println("- ID: " + prof.getId() + ", Nombre: " + prof.getNombreCompleto() + ", Especialidad: " + prof.getEspecialidad() + ", Experiencia: " + prof.getExperienciaAnios() + " años");
            }
        };
    }
}
