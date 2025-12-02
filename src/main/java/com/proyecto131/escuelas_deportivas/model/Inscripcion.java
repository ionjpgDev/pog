package com.proyecto131.escuelas_deportivas.model;
import com.proyecto131.escuelas_deportivas.model.Alumno;
import com.proyecto131.escuelas_deportivas.model.Curso;
public class Inscripcion {
    private String id;
    private Alumno estudiante;
    private Curso curso;
    private String fechaInscripcion;
    private boolean activa;

    public Inscripcion(Alumno estudiante, Curso curso) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fechaInscripcion = java.time.LocalDate.now().toString();
        this.activa = true;
        this.id = "INS" + System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public Alumno getEstudiante() {
        return estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Curso: " + curso.getNombreCurso());
        System.out.println("Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellidos());
        System.out.println("Fecha: " + fechaInscripcion);
        System.out.println("Activa: " + activa);
    }
}
