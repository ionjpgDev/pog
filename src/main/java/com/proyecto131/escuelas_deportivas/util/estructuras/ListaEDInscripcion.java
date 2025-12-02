package com.proyecto131.escuelas_deportivas.util.estructuras;
import com.proyecto131.escuelas_deportivas.model.Inscripcion;
public class ListaEDInscripcion {
    private NodoInscripcion primero;
    private int tamaño;

    public ListaEDInscripcion() {
        this.primero = null;
        this.tamaño = 0;
    }

    public void insertarInscripcion(Inscripcion inscripcion) {
        NodoInscripcion nuevo = new NodoInscripcion(inscripcion);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoInscripcion actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamaño++;
    }

    public Inscripcion eliminarInscripcion(String id) {
        if (primero == null) return null;

        if (primero.getDato().getId().equals(id)) {
            Inscripcion eliminado = primero.getDato();
            primero = primero.getSiguiente();
            tamaño--;
            return eliminado;
        }

        NodoInscripcion actual = primero;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().getId().equals(id)) {
                Inscripcion eliminado = actual.getSiguiente().getDato();
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return eliminado;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Inscripcion buscarInscripcion(String id) {
        NodoInscripcion actual = primero;
        while (actual != null) {
            if (actual.getDato().getId().equals(id)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void mostrarInscripciones() {
        NodoInscripcion actual = primero;
        while (actual != null) {
            actual.getDato().mostrarDatos();
            System.out.println("-------------------");
            actual = actual.getSiguiente();
        }
    }

    public int getTamaño() {
        return tamaño;
    }

    public NodoInscripcion getPrimero() {
        return primero;
    }
}
