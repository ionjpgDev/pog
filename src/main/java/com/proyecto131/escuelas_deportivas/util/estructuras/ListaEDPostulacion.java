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
    
    public void ordenarPorPrioridad() {
        if (tamaño <= 1) return;
        
        boolean intercambiado;
        do {
            intercambiado = false;
            NodoPostulacion actual = primero;
            
            while (actual != null && actual.getSiguiente() != null) {
                if (actual.getDato().getNivelPrioridad() < 
                    actual.getSiguiente().getDato().getNivelPrioridad()) {
                    // Intercambiar las postulaciones
                    Postulacion temp = actual.getDato();
                    actual.setDato(actual.getSiguiente().getDato());
                    actual.getSiguiente().setDato(temp);
                    intercambiado = true;
                }
                actual = actual.getSiguiente();
            }
        } while (intercambiado);
    }
    
    public void mostrarPostulaciones() {
        NodoPostulacion actual = primero;
        int contador = 1;
        while (actual != null) {
            System.out.println(contador + ". " + actual.getDato().getEstudiante().getNombre() + 
                             " " + actual.getDato().getEstudiante().getApellidos() + 
                             " -> " + actual.getDato().getCurso().getNombreCurso() + 
                             " (Prioridad: " + actual.getDato().getNivelPrioridad() + 
                             ", Estado: " + actual.getDato().getEstado() + ")");
            actual = actual.getSiguiente();
            contador++;
        }
        if (tamaño == 0) {
            System.out.println("No hay postulaciones en la lista.");
        }
    }
    
    public void mostrarPostulacionesDetalladas() {
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
    
    public void setPrimero(NodoPostulacion primero) {
        this.primero = primero;
    }
    
    public NodoPostulacion getUltimo() {
        return ultimo;
    }
    
    public void setUltimo(NodoPostulacion ultimo) {
        this.ultimo = ultimo;
    }
}