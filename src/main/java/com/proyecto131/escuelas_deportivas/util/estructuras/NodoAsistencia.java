package com.proyecto131.escuelas_deportivas.util.estructuras;

import com.proyecto131.escuelas_deportivas.model.Asistencia;

public class NodoAsistencia {
    private Asistencia dato;
    private NodoAsistencia siguiente;

    public NodoAsistencia(Asistencia dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public Asistencia getDato() {
        return dato;
    }

    public void setDato(Asistencia dato) {
        this.dato = dato;
    }

    public NodoAsistencia getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoAsistencia siguiente) {
        this.siguiente = siguiente;
    }
}
