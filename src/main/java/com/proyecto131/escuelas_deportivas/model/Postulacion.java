package com.proyecto131.escuelas_deportivas.model;

public class Postulacion {
    private String id;
    private Curso curso;
    private Alumno estudiante;
    private String fechaPostulacion;
    private String estado;
    private int nivelPrioridad;
    
    public Postulacion() {
        this.id = "POST" + System.currentTimeMillis();
        this.fechaPostulacion = java.time.LocalDate.now().toString();
        this.estado = "PENDIENTE";
    }
    
    public Postulacion(Alumno estudiante, Curso curso, int nivelPrioridad) {
        this();
        this.estudiante = estudiante;
        this.curso = curso;
        this.nivelPrioridad = nivelPrioridad;
    }
    
    // Getters y Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Curso getCurso() {
        return curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public Alumno getEstudiante() {
        return estudiante;
    }
    
    public void setEstudiante(Alumno estudiante) {
        this.estudiante = estudiante;
    }
    
    public String getFechaPostulacion() {
        return fechaPostulacion;
    }
    
    public void setFechaPostulacion(String fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public int getNivelPrioridad() {
        return nivelPrioridad;
    }
    
    public void setNivelPrioridad(int nivelPrioridad) {
        this.nivelPrioridad = nivelPrioridad;
    }
    
    public void mostrarDatos() {
        System.out.println("=== DATOS DE POSTULACIÓN ===");
        System.out.println("ID: " + id);
        System.out.println("Curso: " + curso.getNombreCurso());
        System.out.println("Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellidos());
        System.out.println("Fecha: " + fechaPostulacion);
        System.out.println("Estado: " + estado);
        System.out.println("Prioridad: " + nivelPrioridad);
    }
}