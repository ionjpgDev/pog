package com.proyecto131.escuelas_deportivas.service;

import com.proyecto131.escuelas_deportivas.model.*;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDCurso;
import org.springframework.stereotype.Service;

@Service
public class ServicioCursos {
    private ListaEDCurso cursos;
    private ServicioDeportes servicioDeportes;
    private ServicioInstructores servicioInstructores;
    
    public ServicioCursos(ServicioDeportes servicioDeportes, ServicioInstructores servicioInstructores) {
        this.cursos = new ListaEDCurso();
        this.servicioDeportes = servicioDeportes;
        this.servicioInstructores = servicioInstructores;
        cargarDatosEjemplo();
    }
    
    private void cargarDatosEjemplo() {
        // Obtener deportes e instructores de ejemplo
        Deporte futbol = servicioDeportes.buscarDeportePorNombre("Fútbol");
        Deporte natacion = servicioDeportes.buscarDeportePorNombre("Natación");
        Deporte baloncesto = servicioDeportes.buscarDeportePorNombre("Baloncesto");
        
        Instructor roberto = servicioInstructores.buscarInstructorPorCi("1111111");
        Instructor laura = servicioInstructores.buscarInstructorPorCi("2222222");
        
        // Crear cursos de ejemplo
        Curso curso1 = new Curso(futbol, roberto, "Fútbol Infantil", 
                               "Cancha Principal", 20, "Lunes y Miércoles 15:00-17:00");
        
        Curso curso2 = new Curso(natacion, laura, "Natación Inicial", 
                               "Piscina Olímpica", 15, "Martes y Jueves 10:00-12:00");
        
        Curso curso3 = new Curso(baloncesto, roberto, "Baloncesto Juvenil", 
                               "Cancha Cubierta", 18, "Viernes 16:00-18:00");
        
        cursos.insertarCurso(curso1);
        cursos.insertarCurso(curso2);
        cursos.insertarCurso(curso3);
        
        // Agregar cursos a los instructores
        roberto.getCursosAsignados().insertarCurso(curso1);
        roberto.getCursosAsignados().insertarCurso(curso3);
        laura.getCursosAsignados().insertarCurso(curso2);
        
        // Agregar cursos a los deportes
        futbol.getCursos().insertarCurso(curso1);
        natacion.getCursos().insertarCurso(curso2);
        baloncesto.getCursos().insertarCurso(curso3);
    }
    
    public ListaEDCurso obtenerTodosCursos() {
        cursos.ordenarCursosPorFecha();
        return cursos;
    }
    
    public Curso buscarCursoPorId(String id) {
        return cursos.buscarCurso(id);
    }
    
    public Curso buscarCursoPorNombre(String nombre) {
        return cursos.buscarPorNombre(nombre);
    }
    
    public void agregarCurso(Curso curso) {
        cursos.insertarCurso(curso);
        
        // Agregar curso al instructor
        if (curso.getInstructor() != null) {
            curso.getInstructor().getCursosAsignados().insertarCurso(curso);
        }
        
        // Agregar curso al deporte
        if (curso.getDeporte() != null) {
            curso.getDeporte().getCursos().insertarCurso(curso);
        }
    }
    
    public boolean eliminarCurso(String id) {
        Curso curso = cursos.eliminarCurso(id);
        if (curso != null) {
            // Remover curso del instructor
            if (curso.getInstructor() != null) {
                curso.getInstructor().getCursosAsignados().eliminarCurso(id);
            }
            
            // Remover curso del deporte
            if (curso.getDeporte() != null) {
                curso.getDeporte().getCursos().eliminarCurso(id);
            }
            return true;
        }
        return false;
    }
    
    public boolean inscribirAlumnoEnCurso(String cursoId, Alumno alumno) {
        Curso curso = cursos.buscarCurso(cursoId);
        if (curso != null) {
            return curso.agregarAlumno(alumno);
        }
        return false;
    }
    
    public boolean quitarAlumnoDeCurso(String cursoId, String alumnoCi) {
        Curso curso = cursos.buscarCurso(cursoId);
        if (curso != null) {
            return curso.quitarAlumno(alumnoCi);
        }
        return false;
    }
    
    public int cantidadCursos() {
        return cursos.getTamaño();
    }
    
    public ListaEDCurso buscarCursosPorDeporte(String deporteId) {
        ListaEDCurso resultado = new ListaEDCurso();
        Deporte deporte = servicioDeportes.buscarDeportePorId(deporteId);
        
        if (deporte != null) {
            return deporte.getCursos();
        }
        return resultado;
    }
}