package com.digitechfp.demo_spring.controller;

import com.digitechfp.demo_spring.entity.Estudiante;
import com.digitechfp.demo_spring.service.EstudianteService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/estudiantes")

public class EstudianteController {
    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // Listar todos los estudiantes
    @GetMapping
    public List<Estudiante> obtenerTodos() {
        return estudianteService.listarTodos();
    }

    // Buscar estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> //Interrogante para que pueda ser de distinto tipo de objeto
    obtenerPorId(@PathVariable Long id) {
        return estudianteService.buscarPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("mensaje", "El estudiante no existe")));
    }

    // Crear un nuevo estudiante
    public ResponseEntity<?> crearEstudiante(@RequestBody Estudiante estudiante) {
        // Validar si el correo ya existe
        if (estudianteService.existsByCorreo(estudiante.getCorreo())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap(
                            "mensaje", "El correo ya está registrado"
                    ));
        }
        // Si el correo no existe, guardamos el estudiante
        Estudiante nuevoEstudiante =
                estudianteService.guardarEstudiante(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
    }

    //Actualizar un estudiante existente mediante PUT, que reemplaza TODOS los datos del estudiante
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCompleto(
            @PathVariable Long id,
            @RequestBody Estudiante estudianteDatos
    ) {
        return estudianteService.buscarPorId(id)
                .<ResponseEntity<?>>map(estudianteExistente -> {
                    estudianteExistente.setNombre(estudianteDatos.getNombre());
                    estudianteExistente.setEdad(estudianteDatos.getEdad());
                    estudianteExistente.setCorreo(estudianteDatos.getCorreo());

                    Estudiante estudianteActualizado =
                            estudianteService.guardarEstudiante(estudianteExistente);
                    return ResponseEntity.ok(estudianteActualizado);
        })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("mensaje", "El estudiante no existe")));
    }
    // Put actualiza completo, Patch actualiza los datos que se envian
}
