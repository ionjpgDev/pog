package com.proyecto131.escuelas_deportivas.model;
public class Postulacion {
    private String id;
    private Curso curso;
    private Alumno estudiante;
    private String fechaPostulacion;
    private String estado;
    private int nivelPrioridad;

    public Postulacion(Alumno estudiante, Curso curso, int nivelPrioridad) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.nivelPrioridad = nivelPrioridad;
        this.fechaPostulacion = java.time.LocalDate.now().toString();
        this.estado = "PENDIENTE";
        this.id = "POST" + System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public Curso getCurso() {
        return curso;
    }

    public Alumno getEstudiante() {
        return estudiante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Curso: " + curso.getNombreCurso());
        System.out.println("Estudiante: " + estudiante.getNombre() + " " + estudiante.getApellidos());
        System.out.println("Fecha: " + fechaPostulacion);
        System.out.println("Estado: " + estado);
        System.out.println("Prioridad: " + nivelPrioridad);
    }
}
