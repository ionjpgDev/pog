package com.proyecto131.escuelas_deportivas.controller;

import com.proyecto131.escuelas_deportivas.util.estructuras.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/entrenador")
public class EntrenadorController {
    
    @Autowired
    private MainController mainController;
    
    @GetMapping("/panel")
    public String panel(Model model) {
        // Obtener primer instructor
        Instructor instructor = mainController.getInstructores().getPrimero().getDato();
        
        model.addAttribute("nombre", instructor.getNombre() + " " + instructor.getApellidos());
        model.addAttribute("ci", instructor.getCi());
        model.addAttribute("especialidad", "Entrenador Principal");
        
        return "entrenador/panel";
    }
    
    @GetMapping("/cursos")
    public String cursos(Model model) {
        Instructor instructor = mainController.getInstructores().getPrimero().getDato();
        ListaEDCurso cursosInstructor = instructor.getCursosAsignados();
        List<String> cursosList = new ArrayList<>();
        
        // Recorrer cursos asignados al instructor
        NodoCurso actual = cursosInstructor.getPrimero();
        while (actual != null) {
            Curso c = actual.getDato();
            cursosList.add(c.getNombreCurso() + " - " + c.getDeporte().getNombre() + 
                " - Estudiantes: " + c.getEstudiantesOficiales().getTamaño());
            actual = actual.getSiguiente();
        }
        
        model.addAttribute("cursos", cursosList);
        return "entrenador/cursos";
    }
    
    // NUEVA FUNCIONALIDAD: ASISTENCIA usando tu cola
    @GetMapping("/asistencia")
    public String asistencia(@RequestParam(required = false) String cursoId, Model model) {
        ListaEDCurso cursos = mainController.getCursos();
        List<String> estudiantesList = new ArrayList<>();
        String cursoNombre = "Seleccione un curso";
        
        if (cursoId != null) {
            Curso curso = cursos.buscarCurso(cursoId);
            if (curso != null) {
                cursoNombre = curso.getNombreCurso();
                
                // Obtener estudiantes oficiales (lista enlazada)
                ListaEDAlumno estudiantes = curso.getEstudiantesOficiales();
                NodoAlumno actual = estudiantes.getPrimero();
                
                while (actual != null) {
                    Alumno a = actual.getDato();
                    estudiantesList.add(a.getCi() + " - " + a.getNombre() + " " + a.getApellidos());
                    actual = actual.getSiguiente();
                }
                
                // También mostrar lista de espera (cola)
                ColaEDAlumno listaEspera = curso.getListaEspera();
                if (!listaEspera.estaVacia()) {
                    estudiantesList.add("--- LISTA DE ESPERA ---");
                    // Podrías mostrar los primeros en cola
                    Alumno primeroEnEspera = listaEspera.verFrenteAlumno();
                    if (primeroEnEspera != null) {
                        estudiantesList.add("Siguiente en lista: " + primeroEnEspera.getNombre());
                    }
                }
            }
        }
        
        // Listar todos los cursos para seleccionar
        List<String> todosCursos = new ArrayList<>();
        NodoCurso actualCurso = cursos.getPrimero();
        while (actualCurso != null) {
            Curso c = actualCurso.getDato();
            todosCursos.add(c.getId() + " - " + c.getNombreCurso());
            actualCurso = actualCurso.getSiguiente();
        }
        
        model.addAttribute("cursoSeleccionado", cursoNombre);
        model.addAttribute("estudiantes", estudiantesList);
        model.addAttribute("todosCursos", todosCursos);
        return "entrenador/asistencia";
    }
    
    @PostMapping("/asistencia/registrar")
    public String registrarAsistencia(@RequestParam String cursoId,
                                    @RequestParam List<String> estudiantesPresentes) {
        // Implementar registro de asistencia
        // Aquí usarías tu estructura para guardar asistencias
        return "redirect:/entrenador/asistencia?cursoId=" + cursoId;
    }
    
    @GetMapping("/estudiantes")
    public String estudiantes(Model model) {
        ListaEDAlumno alumnos = mainController.getAlumnos();
        List<String> estudiantesList = new ArrayList<>();
        
        // Recorrer lista enlazada de alumnos
        NodoAlumno actual = alumnos.getPrimero();
        while (actual != null) {
            Alumno a = actual.getDato();
            if (a.isActivo()) {
                estudiantesList.add(a.getCi() + " - " + a.getNombre() + " " + a.getApellidos() + 
                    " - " + a.getEmail());
            }
            actual = actual.getSiguiente();
        }
        
        model.addAttribute("estudiantes", estudiantesList);
        return "entrenador/estudiantes";
    }
}