package com.proyecto131.escuelas_deportivas.util.estructuras;

import com.proyecto131.escuelas_deportivas.model.Alumno;

public class ListaEDAlumno {
    private NodoAlumno primero;
    private int tamaño;
    
    public ListaEDAlumno() {
        this.primero = null;
        this.tamaño = 0;
    }
    
    public void insertarAlumno(Alumno alumno) {
        NodoAlumno nuevo = new NodoAlumno(alumno);
        if (primero == null) {
            primero = nuevo;
        } else {
            NodoAlumno actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        tamaño++;
    }
    
    public Alumno eliminarAlumno(String ci) {
        if (primero == null) return null;
        
        if (primero.getDato().getCi().equals(ci)) {
            Alumno eliminado = primero.getDato();
            primero = primero.getSiguiente();
            tamaño--;
            return eliminado;
        }
        
        NodoAlumno actual = primero;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().getCi().equals(ci)) {
                Alumno eliminado = actual.getSiguiente().getDato();
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return eliminado;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public Alumno buscarAlumno(String ci) {
        NodoAlumno actual = primero;
        while (actual != null) {
            if (actual.getDato().getCi().equals(ci)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public void mostrarAlumnos() {
        NodoAlumno actual = primero;
        int contador = 1;
        while (actual != null) {
            System.out.println(contador + ". " + actual.getDato().getNombre() + " " + 
                             actual.getDato().getApellidos() + " (CI: " + 
                             actual.getDato().getCi() + ")");
            actual = actual.getSiguiente();
            contador++;
        }
        if (tamaño == 0) {
            System.out.println("No hay alumnos en la lista.");
        }
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
    
    public NodoAlumno getPrimero() {
        return primero;
    }
    
    public void setPrimero(NodoAlumno primero) {
        this.primero = primero;
    }
}