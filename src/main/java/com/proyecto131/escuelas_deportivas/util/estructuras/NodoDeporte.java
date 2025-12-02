package com.proyecto131.escuelas_deportivas.util.estructuras;
import com.proyecto131.escuelas_deportivas.model.Deporte;
public class NodoDeporte {
    private Deporte dato;
    private NodoDeporte siguiente;

    public NodoDeporte(Deporte dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Deporte getDato() {
        return dato;
    }

    public void setSiguiente(NodoDeporte siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDeporte getSiguiente() {
        return siguiente;
    }
}
