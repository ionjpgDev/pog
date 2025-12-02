package com.proyecto131.escuelas_deportivas.util.estructuras;
import com.proyecto131.escuelas_deportivas.model.Postulacion;
public class ListaEDPostulacion {
    private NodoPostulacion primero;
    private NodoPostulacion ultimo;
    private int tamaño;

    public ListaEDPostulacion() {
        this.primero = null;
        this.ultimo = null;
        this.tamaño = 0;
    }

    public void insertarPostulacion(Postulacion postulacion) {
        NodoPostulacion nuevo = new NodoPostulacion(postulacion);
        if (primero == null) {
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }
        tamaño++;
    }

    public Postulacion eliminarPostulacion(String id) {
        if (primero == null) return null;

        NodoPostulacion actual = primero;
        while (actual != null) {
            if (actual.getDato().getId().equals(id)) {
                if (actual.getAnterior() != null) {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                } else {
                    primero = actual.getSiguiente();
                }

                if (actual.getSiguiente() != null) {
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                } else {
                    ultimo = actual.getAnterior();
                }

                tamaño--;
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Postulacion buscarPostulacion(String id) {
        NodoPostulacion actual = primero;
        while (actual != null) {
            if (actual.getDato().getId().equals(id)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void mostrarPostulaciones() {
        NodoPostulacion actual = primero;
        while (actual != null) {
            actual.getDato().mostrarDatos();
            System.out.println("-------------------");
            actual = actual.getSiguiente();
        }
    }

    public int getTamaño() {
        return tamaño;
    }

    public NodoPostulacion getPrimero() {
        return primero;
    }

    public NodoPostulacion getUltimo() {
        return ultimo;
    }
}