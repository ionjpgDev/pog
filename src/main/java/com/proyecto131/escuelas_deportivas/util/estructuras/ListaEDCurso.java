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
    
    public Curso buscarPorNombre(String nombre) {
        NodoCurso actual = primero;
        while (actual != null) {
            if (actual.getDato().getNombreCurso().equalsIgnoreCase(nombre)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public void ordenarCursosPorFecha() {
        if (tamaño <= 1) return;
        
        boolean intercambiado;
        do {
            intercambiado = false;
            NodoCurso actual = primero;
            
            while (actual != null && actual.getSiguiente() != null) {
                if (actual.getDato().getFechaCreacion()
                    .compareTo(actual.getSiguiente().getDato().getFechaCreacion()) > 0) {
                    // Intercambiar los cursos
                    Curso temp = actual.getDato();
                    actual.setDato(actual.getSiguiente().getDato());
                    actual.getSiguiente().setDato(temp);
                    intercambiado = true;
                }
                actual = actual.getSiguiente();
            }
        } while (intercambiado);
    }
    
    public void mostrarCursos() {
        NodoCurso actual = primero;
        int contador = 1;
        while (actual != null) {
            System.out.println(contador + ". " + actual.getDato().getNombreCurso() + 
                             " (" + actual.getDato().getDeporte().getNombre() + ") - " +
                             actual.getDato().getCupoActual() + "/" + 
                             actual.getDato().getCupoMaximo() + " - " +
                             actual.getDato().getEstado());
            actual = actual.getSiguiente();
            contador++;
        }
        if (tamaño == 0) {
            System.out.println("No hay cursos en la lista.");
        }
    }
    
    public void mostrarCursosDetallados() {
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
    
    public void setPrimero(NodoCurso primero) {
        this.primero = primero;
    }
    
    public NodoCurso getUltimo() {
        return ultimo;
    }
    
    public void setUltimo(NodoCurso ultimo) {
        this.ultimo = ultimo;
    }
}