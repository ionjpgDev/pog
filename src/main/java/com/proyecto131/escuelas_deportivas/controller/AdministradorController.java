package com.proyecto131.escuelas_deportivas.controller;

import com.proyecto131.escuelas_deportivas.util.estructuras.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.proyecto131.escuelas_deportivas.model.Alumno;
import com.proyecto131.escuelas_deportivas.model.Curso;
import com.proyecto131.escuelas_deportivas.model.Postulacion;
import com.proyecto131.escuelas_deportivas.model.Instructor;
import com.proyecto131.escuelas_deportivas.model.Deporte;
import com.proyecto131.escuelas_deportivas.model.Inscripcion;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoAlumno;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoCurso;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoPostulacion;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoDeporte;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoInscripcion;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDAlumno;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDCurso;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDDeporte;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDPostulacion;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDInscripcion;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @Autowired
    private MainController mainController;
    
    @GetMapping("/panel")
    public String panel(Model model) {
        ListaEDAlumno alumnos = mainController.getAlumnos();
        ListaEDInstructor instructores = mainController.getInstructores();
        ListaEDCurso cursos = mainController.getCursos();
        ListaEDDeporte deportes = mainController.getDeportes();
        
        model.addAttribute("totalAlumnos", alumnos.getTamaño());
        model.addAttribute("totalInstructores", instructores.getTamaño());
        model.addAttribute("totalCursos", cursos.getTamaño());
        model.addAttribute("totalDeportes", deportes.getTamaño());
        
        return "administrador/panel";
    }
    
    @GetMapping("/alumnos")
    public String alumnos(Model model) {
        ListaEDAlumno alumnos = mainController.getAlumnos();
        List<String> alumnosList = new ArrayList<>();
        
        // Recorrer lista enlazada
        NodoAlumno actual = alumnos.getPrimero();
        while (actual != null) {
            Alumno a = actual.getDato();
            alumnosList.add(a.getCi() + " - " + a.getNombre() + " " + a.getApellidos() + 
                " - Estado: " + (a.isActivo() ? "ACTIVO" : "INACTIVO"));
            actual = actual.getSiguiente();
        }
        
        model.addAttribute("alumnos", alumnosList);
        return "administrador/alumnos";
    }
    
    @PostMapping("/alumnos/eliminar")
    public String eliminarAlumno(@RequestParam String ci) {
        // Usar tu método eliminar de ListaEDAlumno
        mainController.getAlumnos().eliminar(ci);
        return "redirect:/administrador/alumnos";
    }
    
    @GetMapping("/cursos")
    public String cursos(Model model) {
        ListaEDCurso cursos = mainController.getCursos();
        List<String> cursosList = new ArrayList<>();
        
        // Recorrer lista doblemente enlazada
        NodoCurso actual = cursos.getPrimero();
        while (actual != null) {
            Curso c = actual.getDato();
            cursosList.add(c.getId() + " - " + c.getNombreCurso() + 
                " - Cupo: " + c.getCupoActual() + "/" + c.getCupoMaximo() +
                " - Lista espera: " + c.getListaEspera().getTamaño());
            actual = actual.getSiguiente();
        }
        
        model.addAttribute("cursos", cursosList);
        return "administrador/cursos";
    }
    
    @GetMapping("/postulaciones")
    public String postulaciones(Model model) {
        ListaEDAlumno alumnos = mainController.getAlumnos();
        List<String> postulacionesList = new ArrayList<>();
        
        // Recorrer todos los alumnos y sus postulaciones
        NodoAlumno actualAlumno = alumnos.getPrimero();
        while (actualAlumno != null) {
            Alumno alumno = actualAlumno.getDato();
            ListaEDPostulacion postulacionesAlumno = alumno.getPostulaciones();
            
            // Recorrer postulaciones del alumno
            NodoPostulacion actualPost = postulacionesAlumno.getPrimero();
            while (actualPost != null) {
                Postulacion p = actualPost.getDato();
                postulacionesList.add(alumno.getNombre() + " -> " + 
                    p.getCurso().getNombreCurso() + " - Estado: " + p.getEstado());
                actualPost = actualPost.getSiguiente();
            }
            
            actualAlumno = actualAlumno.getSiguiente();
        }
        
        model.addAttribute("postulaciones", postulacionesList);
        return "administrador/postulaciones";
    }
    
    // Método para aprobar/rechazar postulaciones
    @PostMapping("/postulaciones/aprobar")
    public String aprobarPostulacion(@RequestParam String postulacionId) {
        // Buscar en todas las postulaciones
        ListaEDAlumno alumnos = mainController.getAlumnos();
        
        NodoAlumno actualAlumno = alumnos.getPrimero();
        while (actualAlumno != null) {
            Alumno alumno = actualAlumno.getDato();
            Postulacion post = alumno.getPostulaciones().buscarPostulacion(postulacionId);
            
            if (post != null) {
                post.setEstado("APROBADA");
                // Mover alumno a estudiantes oficiales o lista de espera
                Curso curso = post.getCurso();
                if (curso.tieneCupoDisponible()) {
                    curso.getEstudiantesOficiales().adicionar(alumno);
                    curso.incrementarCupo();
                } else {
                    curso.getListaEspera().adicionar(alumno); // Usando tu COLA
                }
                break;
            }
            
            actualAlumno = actualAlumno.getSiguiente();
        }
        
        return "redirect:/administrador/postulaciones";
    }
}