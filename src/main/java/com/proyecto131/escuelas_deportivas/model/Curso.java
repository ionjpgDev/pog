package com.proyecto131.escuelas_deportivas.model;

import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDAlumno;
import com.proyecto131.escuelas_deportivas.util.estructuras.ColaEDAlumno;
public class Curso {
    private String id;
    private Deporte deporte;
    private Instructor instructor;
    private String nombreCurso;
    private String ubicacion;
    private int cupoActual;
    private int cupoMaximo;
    private String estado;
    private ListaEDAlumno estudiantesOficiales;
    private ColaEDAlumno listaEspera;
    private String fechaCreacion;

    public Curso(Deporte deporte, Instructor instructor, String nombreCurso, String ubicacion, int cupoMaximo) {
        this.deporte = deporte;
        this.instructor = instructor;
        this.nombreCurso = nombreCurso;
        this.ubicacion = ubicacion;
        this.cupoMaximo = cupoMaximo;
        this.cupoActual = 0;
        this.estado = "ACTIVO";
        this.estudiantesOficiales = new ListaEDAlumno();
        this.listaEspera = new ColaEDAlumno();
        this.fechaCreacion = java.time.LocalDate.now().toString();
        this.id = "C" + System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public int getCupoActual() {
        return cupoActual;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public String getEstado() {
        return estado;
    }

    public ListaEDAlumno getEstudiantesOficiales() {
        return estudiantesOficiales;
    }

    public ColaEDAlumno getListaEspera() {
        return listaEspera;
    }

    public void agregarPostulacion(Postulacion postulacion) {
    }

    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombreCurso);
        System.out.println("Deporte: " + deporte.getNombre());
        System.out.println("Ubicacion: " + ubicacion);
        System.out.println("Cupo: " + cupoActual + "/" + cupoMaximo);
        System.out.println("Estado: " + estado);
        System.out.println("Fecha Creacion: " + fechaCreacion);
    }
}
