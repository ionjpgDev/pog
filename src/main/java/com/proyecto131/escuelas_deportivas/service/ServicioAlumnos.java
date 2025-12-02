package com.proyecto131.escuelas_deportivas.service;

import com.proyecto131.escuelas_deportivas.model.Alumno;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDAlumno;
import org.springframework.stereotype.Service;

@Service
public class ServicioAlumnos {
    private ListaEDAlumno alumnos;
    
    public ServicioAlumnos() {
        this.alumnos = new ListaEDAlumno();
        cargarDatosEjemplo();
    }
    
    private void cargarDatosEjemplo() {
        alumnos.insertarAlumno(new Alumno("1234567", "Juan", "Pérez", "juan@email.com", 
                                        "12345678", "2005-03-15", "juan123"));
        alumnos.insertarAlumno(new Alumno("7654321", "María", "Gómez", "maria@email.com", 
                                        "87654321", "2006-07-22", "maria456"));
        alumnos.insertarAlumno(new Alumno("9876543", "Carlos", "López", "carlos@email.com", 
                                        "45678912", "2004-11-30", "carlos789"));
        alumnos.insertarAlumno(new Alumno("2345678", "Ana", "Martínez", "ana@email.com", 
                                        "78912345", "2007-01-10", "ana012"));
        alumnos.insertarAlumno(new Alumno("8765432", "Pedro", "Rodríguez", "pedro@email.com", 
                                        "32165498", "2005-09-05", "pedro345"));
    }
    
    public ListaEDAlumno obtenerTodosAlumnos() {
        return alumnos;
    }
    
    public Alumno buscarAlumnoPorCi(String ci) {
        return alumnos.buscarAlumno(ci);
    }
    
    public void agregarAlumno(Alumno alumno) {
        alumnos.insertarAlumno(alumno);
    }
    
    public boolean eliminarAlumno(String ci) {
        return alumnos.eliminarAlumno(ci) != null;
    }
    
    public boolean autenticarAlumno(String ci, String contrasena) {
        Alumno alumno = alumnos.buscarAlumno(ci);
        return alumno != null && alumno.getContrasena().equals(contrasena);
    }
    
    public int cantidadAlumnos() {
        return alumnos.getTamaño();
    }
}