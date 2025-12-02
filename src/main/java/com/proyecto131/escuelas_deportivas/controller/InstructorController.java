package com.proyecto131.escuelas_deportivas.controller;

import com.proyecto131.escuelas_deportivas.model.Instructor;
import com.proyecto131.escuelas_deportivas.service.ServicioInstructores;
import com.proyecto131.escuelas_deportivas.service.ServicioCursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDInstructor;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoInstructor;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
    
    @Autowired
    private ServicioInstructores servicioInstructores;
    
    @Autowired
    private ServicioCursos servicioCursos;
    
    @GetMapping("/panel")
    public String mostrarPanel(Model model) {
        model.addAttribute("titulo", "Panel del Instructor");
        
        // Obtener la lista enlazada de instructores
        ListaEDInstructor listaInstructores = servicioInstructores.obtenerTodosInstructores();
        
        // Convertir la lista enlazada a una lista estándar de Java
        List<Instructor> instructores = new ArrayList<>();
        if (listaInstructores != null && !listaInstructores.estaVacia()) {
            NodoInstructor actual = listaInstructores.getPrimero();
            while (actual != null) {
                instructores.add(actual.getDato());
                actual = actual.getSiguiente();
            }
        }
        
        model.addAttribute("instructores", instructores);
        return "instructor/panel";
    }
    
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("titulo", "Registrar Instructor");
        model.addAttribute("instructor", new Instructor());
        return "instructor/registrar";
    }
    
    @PostMapping("/registrar")
    public String registrarInstructor(@ModelAttribute Instructor instructor, RedirectAttributes redirectAttributes) {
        servicioInstructores.agregarInstructor(instructor);
        redirectAttributes.addFlashAttribute("mensaje", "Instructor registrado exitosamente");
        return "redirect:/instructor/panel";
    }
    
    @GetMapping("/detalle/{ci}")
    public String verDetalle(@PathVariable String ci, Model model) {
        Instructor instructor = servicioInstructores.buscarInstructorPorCi(ci);
        model.addAttribute("titulo", "Detalle del Instructor");
        model.addAttribute("instructor", instructor);
        return "instructor/detalle";
    }
    
    @GetMapping("/eliminar/{ci}")
    public String eliminarInstructor(@PathVariable String ci, RedirectAttributes redirectAttributes) {
        boolean eliminado = servicioInstructores.eliminarInstructor(ci);
        if (eliminado) {
            redirectAttributes.addFlashAttribute("mensaje", "Instructor eliminado exitosamente");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el instructor");
        }
        return "redirect:/instructor/panel";
    }
    
    @GetMapping("/cursos/{ci}")
    public String verCursosInstructor(@PathVariable String ci, Model model) {
        Instructor instructor = servicioInstructores.buscarInstructorPorCi(ci);
        model.addAttribute("titulo", "Cursos del Instructor");
        model.addAttribute("instructor", instructor);
        return "instructor/cursos";
    }
}