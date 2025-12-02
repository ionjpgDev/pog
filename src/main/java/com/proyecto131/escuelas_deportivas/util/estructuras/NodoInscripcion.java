package com.proyecto131.escuelas_deportivas.util.estructuras;
import com.proyecto131.escuelas_deportivas.model.Inscripcion;
public class NodoInscripcion {
    private Inscripcion dato;
    private NodoInscripcion siguiente;

    public NodoInscripcion(Inscripcion dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Inscripcion getDato() {
        return dato;
    }

    public void setSiguiente(NodoInscripcion siguiente) {
        this.siguiente = siguiente;
    }

    public NodoInscripcion getSiguiente() {
        return siguiente;
    }
}
