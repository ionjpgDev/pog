package com.proyecto131.escuelas_deportivas.service;

import com.proyecto131.escuelas_deportivas.model.Alumno;
import com.proyecto131.escuelas_deportivas.model.Curso;
import com.proyecto131.escuelas_deportivas.model.Inscripcion;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDInscripcion;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoInscripcion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioInscripciones {

    private ListaEDInscripcion inscripciones;

    public ServicioInscripciones() {
        this.inscripciones = new ListaEDInscripcion();
    }

    public void registrarInscripcion(Alumno alumno, Curso curso) {
        if (alumno == null || curso == null) return;
        Inscripcion ins = new Inscripcion(alumno, curso);
        inscripciones.insertarInscripcion(ins);
    }

    public List<Inscripcion> obtenerInscripcionesPorCurso(Curso curso) {
        List<Inscripcion> resultado = new ArrayList<>();
        if (curso == null) return resultado;

        NodoInscripcion actual = inscripciones.getPrimero();
        while (actual != null) {
            Inscripcion ins = actual.getDato();
            if (ins.getCurso() != null && ins.getCurso().getId().equals(curso.getId())) {
                resultado.add(ins);
            }
            actual = actual.getSiguiente();
        }
        return resultado;
    }
}
