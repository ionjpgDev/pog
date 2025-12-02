package com.proyecto131.escuelas_deportivas.model;

import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDCurso;

public class Deporte {
    private String id;
    private String nombre;
    private String tipo;
    private int edadMinima;
    private int edadMaxima;
    private boolean requiereCertificado;
    private boolean activo;
    private ListaEDCurso cursos;
    
    public Deporte() {
        this.id = "D" + System.currentTimeMillis();
        this.activo = true;
        this.cursos = new ListaEDCurso();
    }
    
    public Deporte(String nombre, String tipo, int edadMinima, int edadMaxima, 
                   boolean requiereCertificado) {
        this();
        this.nombre = nombre;
        this.tipo = tipo;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
        this.requiereCertificado = requiereCertificado;
    }
    
    // Getters y Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getEdadMinima() {
        return edadMinima;
    }
    
    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }
    
    public int getEdadMaxima() {
        return edadMaxima;
    }
    
    public void setEdadMaxima(int edadMaxima) {
        this.edadMaxima = edadMaxima;
    }
    
    public boolean isRequiereCertificado() {
        return requiereCertificado;
    }
    
    public void setRequiereCertificado(boolean requiereCertificado) {
        this.requiereCertificado = requiereCertificado;
    }
    
    public boolean isActivo() {
        return activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public ListaEDCurso getCursos() {
        return cursos;
    }
    
    public void setCursos(ListaEDCurso cursos) {
        this.cursos = cursos;
    }
    
    public void mostrarDatos() {
        System.out.println("=== DATOS DEL DEPORTE ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Tipo: " + tipo);
        System.out.println("Edad Mínima: " + edadMinima);
        System.out.println("Edad Máxima: " + edadMaxima);
        System.out.println("Requiere Certificado: " + (requiereCertificado ? "Sí" : "No"));
        System.out.println("Activo: " + (activo ? "Sí" : "No"));
        System.out.println("Cursos: " + cursos.getTamaño());
    }
}