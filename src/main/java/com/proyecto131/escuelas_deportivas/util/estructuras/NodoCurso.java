package com.proyecto131.escuelas_deportivas.util.estructuras;
import com.proyecto131.escuelas_deportivas.model.Curso;

public class NodoCurso {
    private Curso dato;
    private NodoCurso anterior;
    private NodoCurso siguiente;

    public NodoCurso(Curso dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }

    public Curso getDato() {
        return dato;
    }

    public void setAnterior(NodoCurso anterior) {
        this.anterior = anterior;
    }

    public void setSiguiente(NodoCurso siguiente) {
        this.siguiente = siguiente;
    }

    public NodoCurso getAnterior() {
        return anterior;
    }

    public NodoCurso getSiguiente() {
        return siguiente;
    }
}
