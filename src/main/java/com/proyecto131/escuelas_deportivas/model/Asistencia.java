package com.proyecto131.escuelas_deportivas.model;

public class Asistencia {
    private Curso curso;
    private Alumno alumno;
    private String fecha; // formato yyyy-MM-dd
    private boolean presente;

    public Asistencia() {
    }

    public Asistencia(Curso curso, Alumno alumno, String fecha, boolean presente) {
        this.curso = curso;
        this.alumno = alumno;
        this.fecha = fecha;
        this.presente = presente;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }
}
