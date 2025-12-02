package com.proyecto131.escuelas_deportivas.controller;

import com.proyecto131.escuelas_deportivas.model.Alumno;
import com.proyecto131.escuelas_deportivas.service.ServicioAlumnos;
import com.proyecto131.escuelas_deportivas.service.ServicioCursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("alumnos", servicioAlumnos.obtenerTodosAlumnos());
        return "alumno/panel";
    }
    
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("titulo", "Registrar Alumno");
        model.addAttribute("alumno", new Alumno());
        return "alumno/registrar";
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
        model.addAttribute("titulo", "Buscar Alumno");
        model.addAttribute("alumno", alumno);
        return "alumno/detalle";
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
        model.addAttribute("cursos", servicioCursos.obtenerTodosCursos());
        model.addAttribute("alumnos", servicioAlumnos.obtenerTodosAlumnos());
        return "alumno/inscribir";
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