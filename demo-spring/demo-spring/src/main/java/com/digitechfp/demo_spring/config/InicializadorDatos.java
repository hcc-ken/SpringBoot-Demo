package com.digitechfp.demo_spring.config;

import com.digitechfp.demo_spring.entity.Estudiante;
import com.digitechfp.demo_spring.repository.EstudianteRepository;
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
}
