package com.proyecto131.escuelas_deportivas.controller;

import com.proyecto131.escuelas_deportivas.model.Curso;
import com.proyecto131.escuelas_deportivas.service.ServicioCursos;
import com.proyecto131.escuelas_deportivas.service.ServicioDeportes;
import com.proyecto131.escuelas_deportivas.service.ServicioInstructores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/curso")
public class CursoController {
    
    @Autowired
    private ServicioCursos servicioCursos;
    
    @Autowired
    private ServicioDeportes servicioDeportes;
    
    @Autowired
    private ServicioInstructores servicioInstructores;
    
    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("titulo", "Lista de Cursos");
        model.addAttribute("cursos", servicioCursos.obtenerTodosCursos());
        return "curso/listar";
    }
    
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("titulo", "Registrar Curso");
        model.addAttribute("curso", new Curso());
        model.addAttribute("deportes", servicioDeportes.obtenerTodosDeportes());
        model.addAttribute("instructores", servicioInstructores.obtenerTodosInstructores());
        return "curso/registrar";
    }
    
    @PostMapping("/registrar")
    public String registrarCurso(@ModelAttribute Curso curso, 
                                @RequestParam String deporteId,
                                @RequestParam String instructorCi,
                                RedirectAttributes redirectAttributes) {
        
        curso.setDeporte(servicioDeportes.buscarDeportePorId(deporteId));
        curso.setInstructor(servicioInstructores.buscarInstructorPorCi(instructorCi));
        
        servicioCursos.agregarCurso(curso);
        redirectAttributes.addFlashAttribute("mensaje", "Curso registrado exitosamente");
        return "redirect:/curso";
    }
    
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable String id, Model model) {
        Curso curso = servicioCursos.buscarCursoPorId(id);
        model.addAttribute("titulo", "Detalle del Curso");
        model.addAttribute("curso", curso);
        return "curso/detalle";
    }
    
    @GetMapping("/estudiantes/{id}")
    public String verEstudiantes(@PathVariable String id, Model model) {
        Curso curso = servicioCursos.buscarCursoPorId(id);
        model.addAttribute("titulo", "Estudiantes del Curso");
        model.addAttribute("curso", curso);
        return "curso/estudiantes";
    }
    
    @GetMapping("/lista-espera/{id}")
    public String verListaEspera(@PathVariable String id, Model model) {
        Curso curso = servicioCursos.buscarCursoPorId(id);
        model.addAttribute("titulo", "Lista de Espera");
        model.addAttribute("curso", curso);
        return "curso/lista-espera";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable String id, RedirectAttributes redirectAttributes) {
        boolean eliminado = servicioCursos.eliminarCurso(id);
        if (eliminado) {
            redirectAttributes.addFlashAttribute("mensaje", "Curso eliminado exitosamente");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el curso");
        }
        return "redirect:/curso";
    }
}