package com.proyecto131.escuelas_deportivas.util.estructuras;

import com.proyecto131.escuelas_deportivas.model.Alumno;

public class ColaEDAlumno {
    private NodoAlumno frente;
    private NodoAlumno fin;
    private int tamaño;
    
    public ColaEDAlumno() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }
    
    public void encolarAlumno(Alumno alumno) {
        NodoAlumno nuevo = new NodoAlumno(alumno);
        if (frente == null) {
            frente = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamaño++;
    }
    
    public Alumno desencolarAlumno() {
        if (frente == null) return null;
        
        Alumno alumno = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        tamaño--;
        return alumno;
    }
    
    public Alumno verFrenteAlumno() {
        if (frente == null) return null;
        return frente.getDato();
    }
    
    public int getTamaño() {
        return tamaño;
    }
    
    public int getTamanio() {
        return tamaño;
    }
    
    public boolean estaVacia() {
        return frente == null;
    }
    
    public NodoAlumno getFrente() {
        return frente;
    }
    
    public void setFrente(NodoAlumno frente) {
        this.frente = frente;
    }
    
    public NodoAlumno getFin() {
        return fin;
    }
    
    public void setFin(NodoAlumno fin) {
        this.fin = fin;
    }
}