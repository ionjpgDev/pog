package com.proyecto131.escuelas_deportivas.controller;

import com.proyecto131.escuelas_deportivas.util.estructuras.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.proyecto131.escuelas_deportivas.model.*;
import com.proyecto131.escuelas_deportivas.util.estructuras.*;

import javax.annotation.PostConstruct;

@Controller
public class MainController {
    
    // TUS ESTRUCTURAS EXACTAS
    private ListaEDAlumno alumnos;
    private ListaEDDeporte deportes;
    private ListaEDCurso cursos;
    private ListaEDInstructor instructores;  // ESTA LA TIENES
    
    @PostConstruct
    public void init() {
        // Inicializar IGUAL que en tu Main.java original
        alumnos = new ListaEDAlumno();
        deportes = new ListaEDDeporte();
        cursos = new ListaEDCurso();
        instructores = new ListaEDInstructor();  // Esta sí la tienes
        
        cargarDatosPrueba();
    }
    
    private void cargarDatosPrueba() {
        // COPIADO DIRECTAMENTE DE TU Main.java
        Instructor instructor1 = new Instructor("1234567", "Juan", "Pereira", 
            "juan@umsa.com", "1231233424", "20/01/2000", "01/01/2023", true, "pass123");
        Instructor instructor2 = new Instructor("7654321", "Maria", "Lopez", 
            "maria@umsa.com", "78888888", "20/08/1985", "01/01/2023", true, "pass123");
        Instructor instructor3 = new Instructor("1122334", "Carlos", "Garcia", 
            "carlos@umsa.com", "79999999", "10/12/1978", "01/01/2023", true, "pass123");

        instructores.adicionar(instructor1);
        instructores.adicionar(instructor2);
        instructores.adicionar(instructor3);

        Deporte futbol = new Deporte("Futbol", "Grupal", 16, 35, true);
        Deporte natacion = new Deporte("Natación", "Individual", 12, 50, true);
        Deporte basquet = new Deporte("Básquet", "Grupal", 15, 40, false);
        Deporte atletismo = new Deporte("Atletismo", "Individual", 14, 45, true);

        deportes.adicionar(futbol);
        deportes.adicionar(natacion);
        deportes.adicionar(basquet);
        deportes.adicionar(atletismo);

        Curso curso1 = new Curso(futbol, instructor1, "Futbol Principiantes", "Cancha Central", 20);
        Curso curso2 = new Curso(natacion, instructor2, "Natación Avanzada", "Piscina Olímpica", 15);
        Curso curso3 = new Curso(basquet, instructor3, "Básquet Intermedio", "Coliseo UMSA", 18);
        Curso curso4 = new Curso(atletismo, instructor1, "Atletismo Competitivo", "Pista Atlética", 12);

        cursos.adicionar(curso1);
        cursos.adicionar(curso2);
        cursos.adicionar(curso3);
        cursos.adicionar(curso4);

        futbol.getCursos().adicionar(curso1);
        natacion.getCursos().adicionar(curso2);
        basquet.getCursos().adicionar(curso3);
        atletismo.getCursos().adicionar(curso4);

        instructor1.getCursosAsignados().adicionar(curso1);
        instructor2.getCursosAsignados().adicionar(curso2);
        instructor3.getCursosAsignados().adicionar(curso3);
        instructor1.getCursosAsignados().adicionar(curso4);

        Alumno alumno1 = new Alumno("1000001", "Jhonatan", "Plata cruz", "sadsa@uasd.com", 
            "qwe6546", "30/01/2000", "01/01/2023", true, "pass123");
        Alumno alumno2 = new Alumno("1000002", "Luis", "Martinez", "luis@umsa.com", 
            "72222222", "15/08/1999", "01/01/2023", true, "pass123");
        Alumno alumno3 = new Alumno("1000003", "Sofia", "Rodriguez", "sofia@umsa.com", 
            "73333333", "20/03/2001", "01/01/2023", true, "pass123");
        Alumno alumno4 = new Alumno("1000004", "Pedro", "Silva", "pedro@umsa.com", 
            "74444444", "05/12/1998", "01/01/2023", true, "pass123");

        alumnos.adicionar(alumno1);
        alumnos.adicionar(alumno2);
        alumnos.adicionar(alumno3);
        alumnos.adicionar(alumno4);

        alumno1.postularACurso(curso1, 0);
        alumno2.postularACurso(curso1, 1);
        alumno3.postularACurso(curso2, 0);
        alumno4.postularACurso(curso3, 2);
    }
    
    // Rutas principales
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/administrador")
    public String administrador() {
        return "administrador/index";
    }
    
    @GetMapping("/alumno")
    public String alumno() {
        return "alumno/index";
    }
    
    @GetMapping("/entrenador")
    public String entrenador() {
        return "entrenador/index";
    }
    
    // Métodos para que otros controladores accedan a las estructuras
    public ListaEDAlumno getAlumnos() { return alumnos; }
    public ListaEDDeporte getDeportes() { return deportes; }
    public ListaEDCurso getCursos() { return cursos; }
    public ListaEDInstructor getInstructores() { return instructores; }
    
    // Métodos específicos como en tu Main.java
    @GetMapping("/admin/ver-postulaciones")
    @ResponseBody
    public String verPostulacionesCursos() {
        // Implementación COPIADA de tu Main.java
        StringBuilder resultado = new StringBuilder();
        resultado.append("<h2>POSTULACIONES POR CURSO:</h2><br>");
        
        NodoCurso actualCurso = cursos.getPrimero();
        while (actualCurso != null) {
            Curso curso = actualCurso.getDato();
            resultado.append("<b>Curso: ").append(curso.getNombreCurso()).append("</b><br>");
            
            NodoAlumno actualAlumno = alumnos.getPrimero();
            boolean tienePostulaciones = false;
            
            while (actualAlumno != null) {
                Alumno alumno = actualAlumno.getDato();
                NodoPostulacion actualPost = alumno.getPostulaciones().getPrimero();
                
                while (actualPost != null) {
                    Postulacion post = actualPost.getDato();
                    if (post.getCurso().getId().equals(curso.getId())) {
                        if (!tienePostulaciones) {
                            tienePostulaciones = true;
                        }
                        resultado.append("&nbsp;&nbsp;- ")
                                 .append(alumno.getNombre()).append(" ").append(alumno.getApellidos())
                                 .append(" (Estado: ").append(post.getEstado()).append(")<br>");
                    }
                    actualPost = actualPost.getSiguiente();
                }
                actualAlumno = actualAlumno.getSiguiente();
            }
            
            if (!tienePostulaciones) {
                resultado.append("&nbsp;&nbsp;No tiene postulaciones<br>");
            }
            resultado.append("<br>");
            actualCurso = actualCurso.getSiguiente();
        }
        
        return resultado.toString();
    }
}