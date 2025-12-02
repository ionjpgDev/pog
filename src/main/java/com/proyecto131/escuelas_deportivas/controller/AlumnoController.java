package com.proyecto131.escuelas_deportivas.controller;

import com.proyecto131.escuelas_deportivas.util.estructuras.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
    
    @Autowired
    private MainController mainController;
    
    @GetMapping("/panel")
    public String panel(Model model) {
        // Obtener primer alumno de la lista (para demo)
        Alumno alumno = mainController.getAlumnos().getPrimero().getDato();
        
        model.addAttribute("nombre", alumno.getNombre() + " " + alumno.getApellidos());
        model.addAttribute("ci", alumno.getCi());
        model.addAttribute("email", alumno.getEmail());
        
        return "alumno/panel";
    }
    
    @GetMapping("/deportes")
    public String deportes(Model model) {
        ListaEDDeporte deportes = mainController.getDeportes();
        List<String> deportesList = new ArrayList<>();
        
        // Recorrer tu lista enlazada
        NodoDeporte actual = deportes.getPrimero();
        while (actual != null) {
            Deporte d = actual.getDato();
            if (d.isActivo()) {
                deportesList.add(d.getNombre() + " (" + d.getTipo() + ") - Edad: " + 
                    d.getEdadMinima() + "-" + d.getEdadMaxima() + " años");
            }
            actual = actual.getSiguiente();
        }
        
        model.addAttribute("deportes", deportesList);
        return "alumno/deportes";
    }
    
    @GetMapping("/postular")
    public String postular(Model model) {
        ListaEDCurso cursos = mainController.getCursos();
        List<String> cursosList = new ArrayList<>();
        
        // Recorrer tu lista doblemente enlazada de cursos
        NodoCurso actual = cursos.getPrimero();
        while (actual != null) {
            Curso c = actual.getDato();
            if ("ACTIVO".equals(c.getEstado())) {
                cursosList.add(c.getNombreCurso() + " - " + c.getDeporte().getNombre() + 
                    " - Cupo: " + c.getCupoActual() + "/" + c.getCupoMaximo());
            }
            actual = actual.getSiguiente();
        }
        
        model.addAttribute("cursos", cursosList);
        return "alumno/postular";
    }
    
    @PostMapping("/postular")
    public String postularCurso(@RequestParam String cursoId, 
                               @RequestParam int prioridad) {
        // Implementar postulación usando tu lógica original
        Alumno alumno = mainController.getAlumnos().getPrimero().getDato();
        Curso curso = mainController.getCursos().buscarCurso(cursoId);
        
        if (curso != null) {
            alumno.postularACurso(curso, prioridad);
        }
        
        return "redirect:/alumno/postulaciones";
    }
    
    @GetMapping("/postulaciones")
    public String postulaciones(Model model) {
        Alumno alumno = mainController.getAlumnos().getPrimero().getDato();
        ListaEDPostulacion postulaciones = alumno.getPostulaciones();
        List<String> postulacionesList = new ArrayList<>();
        
        // Recorrer tu lista doblemente enlazada de postulaciones
        NodoPostulacion actual = postulaciones.getPrimero();
        while (actual != null) {
            Postulacion p = actual.getDato();
            postulacionesList.add(p.getCurso().getNombreCurso() + 
                " - Estado: " + p.getEstado() + 
                " - Prioridad: " + p.getNivelPrioridad());
            actual = actual.getSiguiente();
        }
        
        model.addAttribute("postulaciones", postulacionesList);
        return "alumno/postulaciones";
    }
    
    @GetMapping("/cursos-inscritos")
    public String cursosInscritos(Model model) {
        // Usando tu cola de alumnos (lista de espera)
        List<String> cursosInscritos = new ArrayList<>();
        
        // Recorrer todos los cursos
        NodoCurso actualCurso = mainController.getCursos().getPrimero();
        while (actualCurso != null) {
            Curso curso = actualCurso.getDato();
            ListaEDAlumno estudiantes = curso.getEstudiantesOficiales();
            
            // Recorrer lista de estudiantes oficiales
            if (estudiantes.getTamaño() > 0) {
                NodoAlumno actualAlumno = estudiantes.getPrimero();
                while (actualAlumno != null) {
                    if (actualAlumno.getDato().getCi().equals("1000001")) { // Demo
                        cursosInscritos.add(curso.getNombreCurso());
                    }
                    actualAlumno = actualAlumno.getSiguiente();
                }
            }
            actualCurso = actualCurso.getSiguiente();
        }
        
        model.addAttribute("cursos", cursosInscritos);
        return "alumno/cursos-inscritos";
    }
}