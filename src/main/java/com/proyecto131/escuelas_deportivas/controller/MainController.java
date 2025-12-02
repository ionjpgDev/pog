package com.proyecto131.escuelas_deportivas.controller;

import com.proyecto131.escuelas_deportivas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    
    @Autowired
    private ServicioDeportes servicioDeportes;
    
    @Autowired
    private ServicioAlumnos servicioAlumnos;
    
    @Autowired
    private ServicioInstructores servicioInstructores;
    
    @Autowired
    private ServicioCursos servicioCursos;
    
    @GetMapping
    public String mostrarInicio(Model model) {
        model.addAttribute("titulo", "Escuelas Deportivas - Inicio");
        model.addAttribute("cantidadDeportes", servicioDeportes.cantidadDeportes());
        model.addAttribute("cantidadAlumnos", servicioAlumnos.cantidadAlumnos());
        model.addAttribute("cantidadInstructores", servicioInstructores.cantidadInstructores());
        model.addAttribute("cantidadCursos", servicioCursos.cantidadCursos());
        return "index";
    }
}