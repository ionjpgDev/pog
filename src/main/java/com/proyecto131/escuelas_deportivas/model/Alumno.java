package com.proyecto131.escuelas_deportivas.model;

import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDCurso;

public class Alumno extends Persona {
    private ListaEDCurso cursosInscritos;
    
    public Alumno(String ci, String nombre, String apellidos, String email, 
                  String telefono, String fechaNacimiento, String contrasena) {
        super(ci, nombre, apellidos, email, telefono, fechaNacimiento, contrasena);
        this.cursosInscritos = new ListaEDCurso();
    }
    
    public Alumno() {
        super();
        this.cursosInscritos = new ListaEDCurso();
    }
    
    public ListaEDCurso getCursosInscritos() {
        return cursosInscritos;
    }
    
    public void setCursosInscritos(ListaEDCurso cursosInscritos) {
        this.cursosInscritos = cursosInscritos;
    }
    
    public void mostrarCursos() {
        System.out.println("=== CURSOS INSCRITOS DE " + nombre.toUpperCase() + " ===");
        if (cursosInscritos.getTamaño() == 0) {
            System.out.println("No tiene cursos inscritos");
        } else {
            cursosInscritos.mostrarCursos();
        }
    }
    
    @Override
    public void mostrarDatos() {
        System.out.println("=== DATOS DEL ALUMNO ===");
        super.mostrarDatos();
        System.out.println("Cursos inscritos: " + cursosInscritos.getTamaño());
    }
}