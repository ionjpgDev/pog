package com.proyecto131.escuelas_deportivas.util.estructuras;

import com.proyecto131.escuelas_deportivas.model.Alumno;

public class NodoAlumno {
    private Alumno dato;
    private NodoAlumno siguiente;
    
    public NodoAlumno(Alumno dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    
    public Alumno getDato() {
        return dato;
    }
    
    public void setDato(Alumno dato) {
        this.dato = dato;
    }
    
    public NodoAlumno getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(NodoAlumno siguiente) {
        this.siguiente = siguiente;
    }
}