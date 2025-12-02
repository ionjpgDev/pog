package com.proyecto131.escuelas_deportivas.util.estructuras;
import com.proyecto131.escuelas_deportivas.model.Instructor;
public class ListaEDInstructor {
    private NodoInstructor primero;
    private int tamaño;

    public ListaEDInstructor() {
        this.primero = null;
        this.tamaño = 0;
    }

    public void adicionar(Instructor instructor) {
        NodoInstructor nuevo = new NodoInstructor(instructor);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoInstructor actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamaño++;
    }

    public Instructor eliminar(String ci) {
        if (primero == null) return null;

        if (primero.getDato().getCi().equals(ci)) {
            Instructor eliminado = primero.getDato();
            primero = primero.getSiguiente();
            tamaño--;
            return eliminado;
        }

        NodoInstructor actual = primero;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().getCi().equals(ci)) {
                Instructor eliminado = actual.getSiguiente().getDato();
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return eliminado;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Instructor buscarInstructor(String ci) {
        NodoInstructor actual = primero;
        while (actual != null) {
            if (actual.getDato().getCi().equals(ci)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void mostrar() {
        NodoInstructor actual = primero;
        while (actual != null) {
            actual.getDato().mostrarDatos();
            System.out.println("-------------------");
            actual = actual.getSiguiente();
        }
    }

    public int getTamaño() {
        return tamaño;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public NodoInstructor getPrimero() {
        return primero;
    }
}