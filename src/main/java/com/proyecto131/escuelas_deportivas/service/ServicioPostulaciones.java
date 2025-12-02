package com.proyecto131.escuelas_deportivas.service;

import com.proyecto131.escuelas_deportivas.model.Alumno;
import com.proyecto131.escuelas_deportivas.model.Curso;
import com.proyecto131.escuelas_deportivas.model.Postulacion;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDPostulacion;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoPostulacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioPostulaciones {

    private ListaEDPostulacion postulaciones;

    @Autowired
    private ServicioInscripciones servicioInscripciones;

    public ServicioPostulaciones() {
        this.postulaciones = new ListaEDPostulacion();
    }

    public void crearPostulacion(Alumno alumno, Curso curso, int prioridad) {
        Postulacion p = new Postulacion(alumno, curso, prioridad);
        postulaciones.insertarPostulacion(p);
    }

    public ListaEDPostulacion obtenerTodas() {
        return postulaciones;
    }

    public List<Postulacion> obtenerPendientesComoLista() {
        List<Postulacion> resultado = new ArrayList<>();
        NodoPostulacion actual = postulaciones.getPrimero();
        while (actual != null) {
            if ("PENDIENTE".equalsIgnoreCase(actual.getDato().getEstado())) {
                resultado.add(actual.getDato());
            }
            actual = actual.getSiguiente();
        }
        return resultado;
    }

    public Postulacion buscarPorId(String id) {
        return postulaciones.buscarPostulacion(id);
    }

    public void aceptar(String id) {
        Postulacion p = postulaciones.buscarPostulacion(id);
        if (p != null && "PENDIENTE".equalsIgnoreCase(p.getEstado())) {
            Curso curso = p.getCurso();
            if (curso != null) {
                curso.agregarPostulacion(p);
                // Si la postulación fue aceptada al curso (no solo puesta en espera), registramos una inscripción
                if ("ACEPTADO".equalsIgnoreCase(p.getEstado())) {
                    servicioInscripciones.registrarInscripcion(p.getEstudiante(), curso);
                }
            }
        }
    }

    public void rechazar(String id) {
        Postulacion p = postulaciones.buscarPostulacion(id);
        if (p != null && "PENDIENTE".equalsIgnoreCase(p.getEstado())) {
            p.setEstado("RECHAZADO");
        }
    }
}
