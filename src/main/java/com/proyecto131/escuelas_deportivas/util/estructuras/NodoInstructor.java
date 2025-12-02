package com.proyecto131.escuelas_deportivas.util.estructuras;

import com.proyecto131.escuelas_deportivas.model.Instructor;

public class NodoInstructor {
    private Instructor dato;
    private NodoInstructor siguiente;
    
    public NodoInstructor(Instructor dato) {
        this.dato = dato;
        this.siguiente = null;
    }
    
    public Instructor getDato() {
        return dato;
    }
    
    public void setDato(Instructor dato) {
        this.dato = dato;
    }
    
    public NodoInstructor getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(NodoInstructor siguiente) {
        this.siguiente = siguiente;
    }
}