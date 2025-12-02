package com.proyecto131.escuelas_deportivas.service;

import com.proyecto131.escuelas_deportivas.model.Alumno;
import com.proyecto131.escuelas_deportivas.model.Curso;
import com.proyecto131.escuelas_deportivas.model.Inscripcion;
import com.proyecto131.escuelas_deportivas.model.Postulacion;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDCurso;
import com.proyecto131.escuelas_deportivas.util.estructuras.NodoCurso;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ServicioPostulacionesTest {

    @Autowired
    private ServicioPostulaciones servicioPostulaciones;

    @Autowired
    private ServicioCursos servicioCursos;

    @Autowired
    private ServicioAlumnos servicioAlumnos;

    @Autowired
    private ServicioInscripciones servicioInscripciones;

    private Curso obtenerPrimerCurso() {
        ListaEDCurso lista = servicioCursos.obtenerTodosCursos();
        if (lista == null || lista.estaVacia()) {
            return null;
        }
        NodoCurso primero = lista.getPrimero();
        return primero != null ? primero.getDato() : null;
    }

    @Test
    void aceptarPostulacion_debeAgregarAlumnoAlCursoYRegistrarInscripcion() {
        Curso curso = obtenerPrimerCurso();
        Assertions.assertNotNull(curso, "Debe existir al menos un curso de ejemplo");

        // Tomamos un alumno de ejemplo conocido del ServicioAlumnos
        Alumno alumno = servicioAlumnos.buscarAlumnoPorCi("1234567");
        Assertions.assertNotNull(alumno, "Debe existir el alumno de ejemplo con CI 1234567");

        // Creamos una postulación pendiente
        servicioPostulaciones.crearPostulacion(alumno, curso, 3);
        List<Postulacion> pendientes = servicioPostulaciones.obtenerPendientesComoLista();
        Assertions.assertFalse(pendientes.isEmpty(), "Debe existir al menos una postulación pendiente");

        Postulacion postulacion = pendientes.get(pendientes.size() - 1); // la última creada

        // Aceptamos la postulación
        servicioPostulaciones.aceptar(postulacion.getId());

        // Verificamos que el alumno quedó aceptado en el curso o al menos como inscrito
        boolean alumnoEnCurso = false;
        if (curso.getEstudiantesOficiales() != null && !curso.getEstudiantesOficiales().estaVacia()) {
            com.proyecto131.escuelas_deportivas.util.estructuras.NodoAlumno actual = curso.getEstudiantesOficiales().getPrimero();
            while (actual != null) {
                if (actual.getDato().getCi().equals(alumno.getCi())) {
                    alumnoEnCurso = true;
                    break;
                }
                actual = actual.getSiguiente();
            }
        }

        Assertions.assertTrue(alumnoEnCurso, "El alumno debería estar entre los estudiantes oficiales del curso tras aceptar la postulación");

        // Verificamos que se registró una inscripción en el historial
        List<Inscripcion> inscripciones = servicioInscripciones.obtenerInscripcionesPorCurso(curso);
        boolean inscripcionEncontrada = inscripciones.stream()
                .anyMatch(ins -> ins.getEstudiante().getCi().equals(alumno.getCi()));

        Assertions.assertTrue(inscripcionEncontrada, "Debe existir una inscripción registrada para el alumno en el curso");
    }

    @Test
    void rechazarPostulacion_noDebeRegistrarInscripcion() {
        Curso curso = obtenerPrimerCurso();
        Assertions.assertNotNull(curso, "Debe existir al menos un curso de ejemplo");

        Alumno alumno = servicioAlumnos.buscarAlumnoPorCi("7654321");
        Assertions.assertNotNull(alumno, "Debe existir el alumno de ejemplo con CI 7654321");

        // Creamos una nueva postulación pendiente
        servicioPostulaciones.crearPostulacion(alumno, curso, 2);
        List<Postulacion> pendientesAntes = servicioPostulaciones.obtenerPendientesComoLista();
        Assertions.assertFalse(pendientesAntes.isEmpty(), "Debe existir al menos una postulación pendiente");

        Postulacion postulacion = pendientesAntes.get(pendientesAntes.size() - 1);

        // Rechazamos la postulación
        servicioPostulaciones.rechazar(postulacion.getId());

        // Aseguramos que el estado sea RECHAZADO
        Postulacion actualizada = servicioPostulaciones.buscarPorId(postulacion.getId());
        Assertions.assertEquals("RECHAZADO", actualizada.getEstado(), "El estado de la postulación debe ser RECHAZADO");

        // Verificamos que NO se registró inscripción para este alumno en este curso
        List<Inscripcion> inscripciones = servicioInscripciones.obtenerInscripcionesPorCurso(curso);
        boolean inscripcionEncontrada = inscripciones.stream()
                .anyMatch(ins -> ins.getEstudiante().getCi().equals(alumno.getCi()));

        Assertions.assertFalse(inscripcionEncontrada, "No debe existir una inscripción registrada para un alumno cuya postulación fue rechazada");
    }
}
