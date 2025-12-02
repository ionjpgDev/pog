package com.proyecto131.escuelas_deportivas.util.estructuras;

import com.proyecto131.escuelas_deportivas.model.Asistencia;

public class ListaEDAsistencia {
    private NodoAsistencia primero;
    private int tamaño;

    public ListaEDAsistencia() {
        this.primero = null;
        this.tamaño = 0;
    }

    public void insertarAsistencia(Asistencia asistencia) {
        NodoAsistencia nuevo = new NodoAsistencia(asistencia);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoAsistencia actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamaño++;
    }

    public int getTamaño() {
        return tamaño;
    }

    public int getTamanio() {
        return tamaño;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public NodoAsistencia getPrimero() {
        return primero;
    }

    public void setPrimero(NodoAsistencia primero) {
        this.primero = primero;
    }
}
