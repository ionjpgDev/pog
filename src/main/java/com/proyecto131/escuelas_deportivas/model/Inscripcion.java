package com.proyecto131.escuelas_deportivas.model;

public class Inscripcion {
    private String id;
    private Alumno estudiante;
    private Curso curso;
    private String fechaInscripcion;
    private boolean activa;
    
    public Inscripcion() {
        this.id = "INS" + System.currentTimeMillis();
        this.fechaInscripcion = java.time.LocalDate.now().toString();
        this.activa = true;
    }
    
    public Inscripcion(Alumno estudiante, Curso curso) {
        this();
        this.estudiante = estudiante;
        this.curso = curso;
    }
    
    // Getters y Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Alumno getEstudiante() {
        return estudiante;
    }
    
    public void setEstudiante(Alumno estudiante) {
        this.estudiante = estudiante;
    }
    
    public Curso getCurso() {
        return curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public String getFechaInscripcion() {
        return fechaInscripcion;
    }
    
    public void setFechaInscripcion(String fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }
    
    public boolean isActiva() {
        return activa;
    }
    
    public void setActiva(boolean activa) {
        this.activa = activa;
    }
    
    public void mostrarDatos() {
        System.out.println("=== DATOS DE INSCRIPCIÓN ===");
        System.out.println("ID: " + id);
        System.out.println("Curso: " + curso.getNombreCurso());
        System.out.println("Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellidos());
        System.out.println("Fecha: " + fechaInscripcion);
        System.out.println("Activa: " + (activa ? "Sí" : "No"));
    }
}