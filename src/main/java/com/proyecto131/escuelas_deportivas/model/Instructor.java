package com.proyecto131.escuelas_deportivas.model;

import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDCurso;

public class Instructor extends Persona {
    private ListaEDCurso cursosAsignados;
    
    public Instructor(String ci, String nombre, String apellidos, String email, 
                      String telefono, String fechaNacimiento, String contrasena) {
        super(ci, nombre, apellidos, email, telefono, fechaNacimiento, contrasena);
        this.cursosAsignados = new ListaEDCurso();
    }
    
    public Instructor() {
        super();
        this.cursosAsignados = new ListaEDCurso();
    }
    
    public ListaEDCurso getCursosAsignados() {
        return cursosAsignados;
    }
    
    public void setCursosAsignados(ListaEDCurso cursosAsignados) {
        this.cursosAsignados = cursosAsignados;
    }
    
    @Override
    public void mostrarDatos() {
        System.out.println("=== DATOS DEL INSTRUCTOR ===");
        super.mostrarDatos();
        System.out.println("Cursos asignados: " + cursosAsignados.getTamaño());
    }
    
    public void mostrarCursos() {
        System.out.println("=== CURSOS ASIGNADOS ===");
        if (cursosAsignados.getTamaño() == 0) {
            System.out.println("No tiene cursos asignados");
        } else {
            cursosAsignados.mostrarCursos();
        }
    }
}