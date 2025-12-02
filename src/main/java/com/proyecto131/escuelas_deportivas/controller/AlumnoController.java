package com.proyecto131.escuelas_deportivas.controller;

import com.proyecto131.escuelas_deportivas.model.Alumno;
import com.proyecto131.escuelas_deportivas.service.ServicioAlumnos;
import com.proyecto131.escuelas_deportivas.service.ServicioCursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto131.escuelas_deportivas.model.Alumno;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDAlumno;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoAlumno;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
    
    @Autowired
    private ServicioAlumnos servicioAlumnos;
    
    @Autowired
    private ServicioCursos servicioCursos;
    
    @GetMapping("/panel")
    public String mostrarPanel(Model model) {
        model.addAttribute("titulo", "Panel del Alumno");
        
        // Obtener la lista enlazada de alumnos
        ListaEDAlumno listaAlumnos = servicioAlumnos.obtenerTodosAlumnos();
        
        // Convertir la lista enlazada a una lista estándar de Java
        List<Alumno> alumnos = new ArrayList<>();
        if (listaAlumnos != null && !listaAlumnos.estaVacia()) {
            NodoAlumno actual = listaAlumnos.getPrimero();
            while (actual != null) {
                alumnos.add(actual.getDato());
                actual = actual.getSiguiente();
            }
        }
        
        model.addAttribute("alumnos", alumnos);
        return "alumnos/panel";
    }
    
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("titulo", "Registrar Alumno");
        model.addAttribute("alumno", new Alumno());
        return "alumnos/registrar";
    }
    
    @PostMapping("/registrar")
    public String registrarAlumno(@ModelAttribute Alumno alumno, RedirectAttributes redirectAttributes) {
        servicioAlumnos.agregarAlumno(alumno);
        redirectAttributes.addFlashAttribute("mensaje", "Alumno registrado exitosamente");
        return "redirect:/alumno/panel";
    }
    
    @GetMapping("/buscar")
    public String buscarAlumno(@RequestParam String ci, Model model) {
        Alumno alumno = servicioAlumnos.buscarAlumnoPorCi(ci);
        model.addAttribute("titulo", "Detalle del Alumno");
        model.addAttribute("alumno", alumno);
        return "alumnos/detalle";
    }
    
    @GetMapping("/detalle/{ci}")
    public String verDetalleAlumno(@PathVariable String ci, Model model) {
        Alumno alumno = servicioAlumnos.buscarAlumnoPorCi(ci);
        model.addAttribute("titulo", "Detalle del Alumno");
        model.addAttribute("alumno", alumno);
        return "alumnos/detalle";
    }
 
    @GetMapping("/eliminar/{ci}")
    public String eliminarAlumno(@PathVariable String ci, RedirectAttributes redirectAttributes) {
        boolean eliminado = servicioAlumnos.eliminarAlumno(ci);
        if (eliminado) {
            redirectAttributes.addFlashAttribute("mensaje", "Alumno eliminado exitosamente");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el alumno");
        }
        return "redirect:/alumno/panel";
    }
    
    @GetMapping("/inscribir")
    public String mostrarInscripcion(Model model) {
        model.addAttribute("titulo", "Inscribir en Curso");
        
        // Convertir lista enlazada de alumnos a List<Alumno>
        ListaEDAlumno listaAlumnos = servicioAlumnos.obtenerTodosAlumnos();
        List<Alumno> alumnos = new ArrayList<>();
        if (listaAlumnos != null && !listaAlumnos.estaVacia()) {
            NodoAlumno actualAl = listaAlumnos.getPrimero();
            while (actualAl != null) {
                alumnos.add(actualAl.getDato());
                actualAl = actualAl.getSiguiente();
            }
        }

        // Convertir lista enlazada de cursos a List<Curso>
        com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDCurso listaCursos = servicioCursos.obtenerTodosCursos();
        List<com.proyecto131.escuelas_deportivas.model.Curso> cursos = new ArrayList<>();
        if (listaCursos != null && !listaCursos.estaVacia()) {
            com.proyecto131.escuelas_deportivas.util.estructuras.NodoCurso actualCur = listaCursos.getPrimero();
            while (actualCur != null) {
                cursos.add(actualCur.getDato());
                actualCur = actualCur.getSiguiente();
            }
        }

        model.addAttribute("alumnos", alumnos);
        model.addAttribute("cursos", cursos);
        return "alumnos/inscribir";
    }
    
    @PostMapping("/inscribir")
    public String inscribirEnCurso(@RequestParam String cursoId, 
                                  @RequestParam String alumnoCi,
                                  RedirectAttributes redirectAttributes) {
        Alumno alumno = servicioAlumnos.buscarAlumnoPorCi(alumnoCi);
        if (alumno != null) {
            boolean inscrito = servicioCursos.inscribirAlumnoEnCurso(cursoId, alumno);
            if (inscrito) {
                redirectAttributes.addFlashAttribute("mensaje", "Alumno inscrito exitosamente");
            } else {
                redirectAttributes.addFlashAttribute("error", "No se pudo inscribir al alumno (curso lleno)");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Alumno no encontrado");
        }
        return "redirect:/alumno/panel";
    }
}