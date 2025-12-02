package com.proyecto131.escuelas_deportivas.service;

import com.proyecto131.escuelas_deportivas.model.Instructor;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDInstructor;
import org.springframework.stereotype.Service;

@Service
public class ServicioInstructores {
    private ListaEDInstructor instructores;
    
    public ServicioInstructores() {
        this.instructores = new ListaEDInstructor();
        cargarDatosEjemplo();
    }
    
    private void cargarDatosEjemplo() {
        instructores.adicionar(new Instructor("1111111", "Roberto", "Fernández", "roberto@email.com", 
                                            "11111111", "1980-05-20", "roberto123"));
        instructores.adicionar(new Instructor("2222222", "Laura", "Silva", "laura@email.com", 
                                            "22222222", "1985-08-15", "laura456"));
        instructores.adicionar(new Instructor("3333333", "Miguel", "Torres", "miguel@email.com", 
                                            "33333333", "1978-12-10", "miguel789"));
    }
    
    public ListaEDInstructor obtenerTodosInstructores() {
        return instructores;
    }
    
    public Instructor buscarInstructorPorCi(String ci) {
        return instructores.buscarInstructor(ci);
    }
    
    public void agregarInstructor(Instructor instructor) {
        instructores.adicionar(instructor);
    }
    
    public boolean eliminarInstructor(String ci) {
        return instructores.eliminar(ci) != null;
    }
    
    public boolean autenticarInstructor(String ci, String contrasena) {
        Instructor instructor = instructores.buscarInstructor(ci);
        return instructor != null && instructor.getContrasena().equals(contrasena);
    }
    
    public int cantidadInstructores() {
        return instructores.getTamaño();
    }
}