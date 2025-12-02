package com.proyecto131.escuelas_deportivas.controller;

import com.proyecto131.escuelas_deportivas.model.Deporte;
import com.proyecto131.escuelas_deportivas.model.Curso;
import com.proyecto131.escuelas_deportivas.service.ServicioDeportes;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDDeporte;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoDeporte;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDCurso;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoCurso;
import com.proyecto131.escuelas_deportivas.service.ServicioCursos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/deporte")
public class DeporteController {

    @Autowired
    private ServicioDeportes servicioDeportes;

    @Autowired
    private ServicioCursos servicioCursos;

    @GetMapping
    public String listarDeportes(Model model) {
        ListaEDDeporte listaED = servicioDeportes.obtenerTodosDeportes();
        // Convertimos la lista enlazada a una lista estándar para Thymeleaf
        List<Deporte> deportes = new ArrayList<>();
        NodoDeporte actual = listaED.getPrimero();
        while (actual != null) {
            deportes.add(actual.getDato());
            actual = actual.getSiguiente();
        }

        model.addAttribute("titulo", "Lista de Deportes");
        model.addAttribute("deportes", deportes); // Ahora pasamos una List<Deporte>
        return "deportes/listar";
    }

    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("titulo", "Registrar Deporte");
        model.addAttribute("deporte", new Deporte());
        return "deportes/registrar";
    }

    @PostMapping("/registrar")
    public String registrarDeporte(@ModelAttribute Deporte deporte, RedirectAttributes redirectAttributes) {
        servicioDeportes.agregarDeporte(deporte);
        redirectAttributes.addFlashAttribute("mensaje", "Deporte registrado exitosamente");
        return "redirect:/deporte";
    }

    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable String id, Model model) {
        Deporte deporte = servicioDeportes.buscarDeportePorId(id);
        model.addAttribute("titulo", "Detalle del Deporte");
        model.addAttribute("deporte", deporte);
        return "deportes/detalle";
    }

    @GetMapping("/cursos/{id}")
    public String verCursosDeporte(@PathVariable String id, Model model) {
        Deporte deporte = servicioDeportes.buscarDeportePorId(id);
        model.addAttribute("titulo", "Cursos del Deporte");
        model.addAttribute("deporte", deporte);

        // Convertir la lista enlazada de cursos del deporte a List<Curso>
        java.util.List<Curso> cursos = new java.util.ArrayList<>();
        if (deporte != null && deporte.getCursos() != null && !deporte.getCursos().estaVacia()) {
            ListaEDCurso listaCursos = deporte.getCursos();
            NodoCurso actual = listaCursos.getPrimero();
            while (actual != null) {
                cursos.add(actual.getDato());
                actual = actual.getSiguiente();
            }
        }
        model.addAttribute("cursos", cursos);

        return "deportes/cursos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDeporte(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean eliminado = servicioDeportes.eliminarDeporte(id);
        if (eliminado) {
            redirectAttributes.addFlashAttribute("mensaje", "Deporte eliminado exitosamente");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el deporte");
        }
        return "redirect:/deporte";
    }
}