package com.proyecto131.escuelas_deportivas.model;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDCurso;

public class Instructor extends Persona {
    private ListaEDCurso cursosAsignados;


    public Instructor(String id,String ci, String nombre, String apellidos, String email, String telefono,
                      String fechaNacimiento, String fechaRegistro, boolean activo, String contrasena) {
        super(id,ci, nombre, apellidos, email, telefono, fechaNacimiento, fechaRegistro, activo, contrasena);
        this.cursosAsignados = new ListaEDCurso();
    }

    public ListaEDCurso getCursosAsignados() {
        return cursosAsignados;
    }

    public void mostrarCursos() {
        System.out.println("CURSOS ASIGNADOS:");
        if (cursosAsignados.getTamaño() == 0) {
            System.out.println("No tiene cursos asignados");
        } else {
            cursosAsignados.mostrarCursos();
        }
    }
}
