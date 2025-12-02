package com.proyecto131.escuelas_deportivas.util.estructuras;
import com.proyecto131.escuelas_deportivas.model.Deporte;
public class ListaEDDeporte {
    private NodoDeporte primero;
    private int tamaño;

    public ListaEDDeporte() {
        this.primero = null;
        this.tamaño = 0;
    }

    public void insertarDeporte(Deporte deporte) {
        NodoDeporte nuevo = new NodoDeporte(deporte);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoDeporte actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamaño++;
    }

    public Deporte eliminarDeporte(String id) {
        if (primero == null) return null;

        if (primero.getDato().getId().equals(id)) {
            Deporte eliminado = primero.getDato();
            primero = primero.getSiguiente();
            tamaño--;
            return eliminado;
        }

        NodoDeporte actual = primero;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().getId().equals(id)) {
                Deporte eliminado = actual.getSiguiente().getDato();
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return eliminado;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public Deporte buscarDeporte(String id) {
        NodoDeporte actual = primero;
        while (actual != null) {
            if (actual.getDato().getId().equals(id)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void mostrarDeportes() {
        NodoDeporte actual = primero;
        while (actual != null) {
            actual.getDato().mostrarDatos();
            System.out.println("-------------------");
            actual = actual.getSiguiente();
        }
    }

    public int getTamaño() {
        return tamaño;
    }

    public NodoDeporte getPrimero() {
        return primero;
    }
}
