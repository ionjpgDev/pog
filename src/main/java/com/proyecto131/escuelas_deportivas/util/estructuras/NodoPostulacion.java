package com.proyecto131.escuelas_deportivas.util.estructuras;

import com.proyecto131.escuelas_deportivas.model.Postulacion;

public class NodoPostulacion {
    private Postulacion dato;
    private NodoPostulacion anterior;
    private NodoPostulacion siguiente;
    
    public NodoPostulacion(Postulacion dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }
    
    public Postulacion getDato() {
        return dato;
    }
    
    public void setDato(Postulacion dato) {
        this.dato = dato;
    }
    
    public NodoPostulacion getAnterior() {
        return anterior;
    }
    
    public void setAnterior(NodoPostulacion anterior) {
        this.anterior = anterior;
    }
    
    public NodoPostulacion getSiguiente() {
        return siguiente;
    }
    
    public void setSiguiente(NodoPostulacion siguiente) {
        this.siguiente = siguiente;
    }
}