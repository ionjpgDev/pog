package com.proyecto131.escuelas_deportivas.controller;

import com.proyecto131.escuelas_deportivas.model.Curso;
import com.proyecto131.escuelas_deportivas.model.Asistencia;
import com.proyecto131.escuelas_deportivas.service.ServicioCursos;
import com.proyecto131.escuelas_deportivas.service.ServicioDeportes;
import com.proyecto131.escuelas_deportivas.service.ServicioInstructores;
import com.proyecto131.escuelas_deportivas.service.ServicioAlumnos;
import com.proyecto131.escuelas_deportivas.service.ServicioPostulaciones;
import com.proyecto131.escuelas_deportivas.service.ServicioInscripciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDCurso;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoCurso;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDDeporte;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoDeporte;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDInstructor;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoInstructor;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("/curso")
public class CursoController {
    
    @Autowired
    private ServicioCursos servicioCursos;
    
    @Autowired
    private ServicioDeportes servicioDeportes;
    
    @Autowired
    private ServicioInstructores servicioInstructores;

    @Autowired
    private ServicioAlumnos servicioAlumnos;

    @Autowired
    private ServicioPostulaciones servicioPostulaciones;

    @Autowired
    private ServicioInscripciones servicioInscripciones;
    
    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("titulo", "Lista de Cursos");
        
        // Obtener la lista enlazada de cursos
        ListaEDCurso listaCursos = servicioCursos.obtenerTodosCursos();
        
        // Convertir la lista enlazada a una lista estándar de Java
        List<Curso> cursos = new ArrayList<>();
        if (listaCursos != null && !listaCursos.estaVacia()) {
            NodoCurso actual = listaCursos.getPrimero();
            while (actual != null) {
                cursos.add(actual.getDato());
                actual = actual.getSiguiente();
            }
        }
        
        model.addAttribute("cursos", cursos);
        return "cursos/listar";
    }
    
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("titulo", "Registrar Curso");
        model.addAttribute("curso", new Curso());

        // Convertir ListaEDDeporte a List<Deporte>
        ListaEDDeporte listaDeportes = servicioDeportes.obtenerTodosDeportes();
        java.util.List<com.proyecto131.escuelas_deportivas.model.Deporte> deportes = new java.util.ArrayList<>();
        if (listaDeportes != null && listaDeportes.getTamaño() > 0) {
            NodoDeporte actualDep = listaDeportes.getPrimero();
            while (actualDep != null) {
                deportes.add(actualDep.getDato());
                actualDep = actualDep.getSiguiente();
            }
        }

        // Convertir ListaEDInstructor a List<Instructor>
        ListaEDInstructor listaInstructores = servicioInstructores.obtenerTodosInstructores();
        java.util.List<com.proyecto131.escuelas_deportivas.model.Instructor> instructores = new java.util.ArrayList<>();
        if (listaInstructores != null && !listaInstructores.estaVacia()) {
            NodoInstructor actualIns = listaInstructores.getPrimero();
            while (actualIns != null) {
                instructores.add(actualIns.getDato());
                actualIns = actualIns.getSiguiente();
            }
        }

        model.addAttribute("deportes", deportes);
        model.addAttribute("instructores", instructores);
        return "cursos/registrar";
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
        return "cursos/detalle";
    }
    
    @GetMapping("/estudiantes/{id}")
    public String verEstudiantes(@PathVariable String id, Model model) {
        Curso curso = servicioCursos.buscarCursoPorId(id);
        model.addAttribute("titulo", "Estudiantes del Curso");
        model.addAttribute("curso", curso);

        // Convertir ListaEDAlumno a List<Alumno> para Thymeleaf
        java.util.List<com.proyecto131.escuelas_deportivas.model.Alumno> estudiantes = new java.util.ArrayList<>();
        if (curso != null && curso.getEstudiantesOficiales() != null && !curso.getEstudiantesOficiales().estaVacia()) {
            com.proyecto131.escuelas_deportivas.util.estructuras.NodoAlumno actual = curso.getEstudiantesOficiales().getPrimero();
            while (actual != null) {
                estudiantes.add(actual.getDato());
                actual = actual.getSiguiente();
            }
        }

        model.addAttribute("estudiantes", estudiantes);
        return "cursos/estudiantes";
    }
    
    @GetMapping("/lista-espera/{id}")
    public String verListaEspera(@PathVariable String id, Model model) {
        Curso curso = servicioCursos.buscarCursoPorId(id);
        model.addAttribute("titulo", "Lista de Espera");
        model.addAttribute("curso", curso);

        // Convertir ColaEDAlumno (lista de espera) a List<Alumno> para Thymeleaf
        java.util.List<com.proyecto131.escuelas_deportivas.model.Alumno> listaEspera = new java.util.ArrayList<>();
        if (curso != null && curso.getListaEspera() != null && !curso.getListaEspera().estaVacia()) {
            com.proyecto131.escuelas_deportivas.util.estructuras.NodoAlumno actual = curso.getListaEspera().getFrente();
            while (actual != null) {
                listaEspera.add(actual.getDato());
                actual = actual.getSiguiente();
            }
        }
        model.addAttribute("listaEspera", listaEspera);

        return "cursos/lista-espera";
    }

    @GetMapping("/inscribir/{id}")
    public String mostrarFormularioInscripcion(@PathVariable String id, Model model) {
        Curso curso = servicioCursos.buscarCursoPorId(id);
        model.addAttribute("titulo", "Inscribir Alumno en Curso");
        model.addAttribute("curso", curso);

        // Convertir ListaEDAlumno (todos los alumnos) a List<Alumno>
        com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDAlumno listaAlumnos = servicioAlumnos.obtenerTodosAlumnos();
        java.util.List<com.proyecto131.escuelas_deportivas.model.Alumno> alumnos = new java.util.ArrayList<>();
        if (listaAlumnos != null && !listaAlumnos.estaVacia()) {
            com.proyecto131.escuelas_deportivas.util.estructuras.NodoAlumno actual = listaAlumnos.getPrimero();
            while (actual != null) {
                alumnos.add(actual.getDato());
                actual = actual.getSiguiente();
            }
        }
        model.addAttribute("alumnos", alumnos);

        return "cursos/inscribir";
    }

    @PostMapping("/inscribir/{id}")
    public String inscribirAlumno(@PathVariable String id,
                                  @RequestParam String alumnoCi,
                                  RedirectAttributes redirectAttributes) {
        Curso curso = servicioCursos.buscarCursoPorId(id);
        if (curso == null) {
            redirectAttributes.addFlashAttribute("error", "Curso no encontrado");
            return "redirect:/curso";
        }

        com.proyecto131.escuelas_deportivas.model.Alumno alumno = servicioAlumnos.buscarAlumnoPorCi(alumnoCi);
        if (alumno == null) {
            redirectAttributes.addFlashAttribute("error", "Alumno no encontrado");
            return "redirect:/curso/inscribir/" + id;
        }

        boolean enOficiales = servicioCursos.inscribirAlumnoEnCurso(id, alumno);
        if (enOficiales) {
            redirectAttributes.addFlashAttribute("mensaje", "Alumno inscrito en el curso");
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Curso lleno: alumno añadido a la lista de espera");
        }

        return "redirect:/curso/detalle/" + id;
    }

    @GetMapping("/postular/{id}")
    public String mostrarFormularioPostulacion(@PathVariable String id, Model model) {
        Curso curso = servicioCursos.buscarCursoPorId(id);
        model.addAttribute("titulo", "Solicitar inscripción");
        model.addAttribute("curso", curso);

        // Lista de todos los alumnos para seleccionar CI
        com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDAlumno listaAlumnos = servicioAlumnos.obtenerTodosAlumnos();
        java.util.List<com.proyecto131.escuelas_deportivas.model.Alumno> alumnos = new java.util.ArrayList<>();
        if (listaAlumnos != null && !listaAlumnos.estaVacia()) {
            com.proyecto131.escuelas_deportivas.util.estructuras.NodoAlumno actual = listaAlumnos.getPrimero();
            while (actual != null) {
                alumnos.add(actual.getDato());
                actual = actual.getSiguiente();
            }
        }
        model.addAttribute("alumnos", alumnos);

        return "cursos/postular";
    }

    @PostMapping("/postular/{id}")
    public String crearPostulacion(@PathVariable String id,
                                   @RequestParam String alumnoCi,
                                   @RequestParam(name = "prioridad", defaultValue = "3") int prioridad,
                                   RedirectAttributes redirectAttributes) {
        Curso curso = servicioCursos.buscarCursoPorId(id);
        if (curso == null) {
            redirectAttributes.addFlashAttribute("error", "Curso no encontrado");
            return "redirect:/curso";
        }

        com.proyecto131.escuelas_deportivas.model.Alumno alumno = servicioAlumnos.buscarAlumnoPorCi(alumnoCi);
        if (alumno == null) {
            redirectAttributes.addFlashAttribute("error", "Alumno no encontrado");
            return "redirect:/curso/postular/" + id;
        }

        servicioPostulaciones.crearPostulacion(alumno, curso, prioridad);
        redirectAttributes.addFlashAttribute("mensaje", "Solicitud de inscripción enviada. Pendiente de revisión.");
        return "redirect:/curso/detalle/" + id;
    }
    
    @GetMapping("/inscripciones/{id}")
    public String verHistorialInscripciones(@PathVariable String id, Model model) {
        Curso curso = servicioCursos.buscarCursoPorId(id);
        model.addAttribute("titulo", "Historial de Inscripciones");
        model.addAttribute("curso", curso);

        java.util.List<com.proyecto131.escuelas_deportivas.model.Inscripcion> inscripciones =
                servicioInscripciones.obtenerInscripcionesPorCurso(curso);
        model.addAttribute("inscripciones", inscripciones);

        return "cursos/inscripciones";
    }
    
    @GetMapping("/asistencia/{id}")
    public String mostrarAsistencia(@PathVariable String id, Model model) {
        Curso curso = servicioCursos.buscarCursoPorId(id);
        model.addAttribute("titulo", "Control de Asistencia");
        model.addAttribute("curso", curso);

        // Lista de estudiantes oficiales para marcar asistencia
        java.util.List<com.proyecto131.escuelas_deportivas.model.Alumno> estudiantes = new java.util.ArrayList<>();
        if (curso != null && curso.getEstudiantesOficiales() != null && !curso.getEstudiantesOficiales().estaVacia()) {
            com.proyecto131.escuelas_deportivas.util.estructuras.NodoAlumno actual = curso.getEstudiantesOficiales().getPrimero();
            while (actual != null) {
                estudiantes.add(actual.getDato());
                actual = actual.getSiguiente();
            }
        }
        model.addAttribute("estudiantes", estudiantes);

        // Historial de asistencia plano para mostrar debajo
        java.util.List<Asistencia> historial = new java.util.ArrayList<>();
        if (curso != null && curso.getRegistrosAsistencia() != null && !curso.getRegistrosAsistencia().estaVacia()) {
            com.proyecto131.escuelas_deportivas.util.estructuras.NodoAsistencia nodo = curso.getRegistrosAsistencia().getPrimero();
            while (nodo != null) {
                historial.add(nodo.getDato());
                nodo = nodo.getSiguiente();
            }
        }
        model.addAttribute("historial", historial);

        return "cursos/asistencia";
    }

    @PostMapping("/asistencia/{id}")
    public String registrarAsistencia(@PathVariable String id,
                                      @RequestParam String fecha,
                                      @RequestParam(name = "presentes", required = false) java.util.List<String> presentesCi,
                                      RedirectAttributes redirectAttributes) {
        Curso curso = servicioCursos.buscarCursoPorId(id);
        if (curso == null) {
            redirectAttributes.addFlashAttribute("error", "Curso no encontrado");
            return "redirect:/curso";
        }

        java.util.List<com.proyecto131.escuelas_deportivas.model.Alumno> estudiantes = new java.util.ArrayList<>();
        if (curso.getEstudiantesOficiales() != null && !curso.getEstudiantesOficiales().estaVacia()) {
            com.proyecto131.escuelas_deportivas.util.estructuras.NodoAlumno actual = curso.getEstudiantesOficiales().getPrimero();
            while (actual != null) {
                estudiantes.add(actual.getDato());
                actual = actual.getSiguiente();
            }
        }

        java.util.Set<String> presentesSet = new java.util.HashSet<>();
        if (presentesCi != null) {
            presentesSet.addAll(presentesCi);
        }

        for (com.proyecto131.escuelas_deportivas.model.Alumno alumno : estudiantes) {
            boolean presente = presentesSet.contains(alumno.getCi());
            Asistencia registro = new Asistencia(curso, alumno, fecha, presente);
            curso.getRegistrosAsistencia().insertarAsistencia(registro);
        }

        redirectAttributes.addFlashAttribute("mensaje", "Asistencia registrada para la fecha " + fecha);
        return "redirect:/curso/asistencia/" + id;
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