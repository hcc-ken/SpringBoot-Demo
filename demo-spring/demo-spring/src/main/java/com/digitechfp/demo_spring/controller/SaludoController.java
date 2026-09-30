package com.digitechfp.demo_spring.controller;

import com.digitechfp.demo_spring.controller.dto.Centroinfodto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController {

    @GetMapping("/saludo")
    public String saludar(@RequestParam(value = "nombre", defaultValue = "Mundo") String nombre){
        return "Hola, " + nombre + " Bienvenido a la clase de SpringBoot de DAM";
    }
    @GetMapping("/info")
    public Centroinfodto info() {
        return new Centroinfodto();
    }
    @GetMapping("/suma")
    public int sumar(@RequestParam int a, @RequestParam int b){
        return a+b;
    }

}
