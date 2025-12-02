package com.proyecto131.escuelas_deportivas.util.estructuras;
import com.proyecto131.escuelas_deportivas.model.Curso;

public class ListaEDCurso {
    private NodoCurso primero;
    private NodoCurso ultimo;
    private int tamaño;

    public ListaEDCurso() {
        this.primero = null;
        this.ultimo = null;
        this.tamaño = 0;
    }

    public void insertarCurso(Curso curso) {
        NodoCurso nuevo = new NodoCurso(curso);
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

    public Curso eliminarCurso(String id) {
        if (primero == null) return null;

        NodoCurso actual = primero;
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

    public Curso buscarCurso(String id) {
        NodoCurso actual = primero;
        while (actual != null) {
            if (actual.getDato().getId().equals(id)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void ordenarCursosPorFecha() {
    }

    public void mostrarCursos() {
        NodoCurso actual = primero;
        while (actual != null) {
            actual.getDato().mostrarDatos();
            System.out.println("-------------------");
            actual = actual.getSiguiente();
        }
    }

    public int getTamaño() {
        return tamaño;
    }

    public NodoCurso getPrimero() {
        return primero;
    }

    public NodoCurso getUltimo() {
        return ultimo;
    }
}
