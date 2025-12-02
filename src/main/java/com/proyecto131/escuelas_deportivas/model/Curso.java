package com.proyecto131.escuelas_deportivas.model;

import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDAlumno;
import com.proyecto131.escuelas_deportivas.util.estructuras.ColaEDAlumno;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDAsistencia;

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
    private ListaEDAsistencia registrosAsistencia;
    private String fechaCreacion;
    private String horario;
    
    public Curso() {
        this.id = "C" + System.currentTimeMillis();
        this.cupoActual = 0;
        this.estado = "ACTIVO";
        this.estudiantesOficiales = new ListaEDAlumno();
        this.listaEspera = new ColaEDAlumno();
        this.registrosAsistencia = new ListaEDAsistencia();
        this.fechaCreacion = java.time.LocalDate.now().toString();
    }
    
    public Curso(Deporte deporte, Instructor instructor, String nombreCurso, 
                 String ubicacion, int cupoMaximo, String horario) {
        this();
        this.deporte = deporte;
        this.instructor = instructor;
        this.nombreCurso = nombreCurso;
        this.ubicacion = ubicacion;
        this.cupoMaximo = cupoMaximo;
        this.horario = horario;
    }
    
    // Getters y Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Deporte getDeporte() {
        return deporte;
    }
    
    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }
    
    public Instructor getInstructor() {
        return instructor;
    }
    
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    public String getNombreCurso() {
        return nombreCurso;
    }
    
    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
    
    public String getUbicacion() {
        return ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    public int getCupoActual() {
        return cupoActual;
    }
    
    public void setCupoActual(int cupoActual) {
        this.cupoActual = cupoActual;
    }
    
    public int getCupoMaximo() {
        return cupoMaximo;
    }
    
    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ListaEDAsistencia getRegistrosAsistencia() {
        return registrosAsistencia;
    }

    public void setRegistrosAsistencia(ListaEDAsistencia registrosAsistencia) {
        this.registrosAsistencia = registrosAsistencia;
    }
    
    public ListaEDAlumno getEstudiantesOficiales() {
        return estudiantesOficiales;
    }
    
    public void setEstudiantesOficiales(ListaEDAlumno estudiantesOficiales) {
        this.estudiantesOficiales = estudiantesOficiales;
    }
    
    public ColaEDAlumno getListaEspera() {
        return listaEspera;
    }
    
    public void setListaEspera(ColaEDAlumno listaEspera) {
        this.listaEspera = listaEspera;
    }
    
    public String getFechaCreacion() {
        return fechaCreacion;
    }
    
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public String getHorario() {
        return horario;
    }
    
    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    // Métodos de negocio
    public boolean agregarAlumno(Alumno alumno) {
        if (cupoActual < cupoMaximo) {
            estudiantesOficiales.insertarAlumno(alumno);
            cupoActual++;
            
            if (cupoActual == cupoMaximo) {
                estado = "COMPLETO";
            }
            return true;
        } else {
            listaEspera.encolarAlumno(alumno);
            return false;
        }
    }
    
    public boolean quitarAlumno(String ci) {
        Alumno alumno = estudiantesOficiales.eliminarAlumno(ci);
        if (alumno != null) {
            cupoActual--;
            estado = "ACTIVO";
            
            // Si hay lista de espera, agregar al primero
            if (!listaEspera.estaVacia()) {
                Alumno siguiente = listaEspera.desencolarAlumno();
                estudiantesOficiales.insertarAlumno(siguiente);
                cupoActual++;
                
                if (cupoActual == cupoMaximo) {
                    estado = "COMPLETO";
                }
            }
            return true;
        }
        return false;
    }
    
    public void agregarPostulacion(Postulacion postulacion) {
        if (cupoActual < cupoMaximo) {
            estudiantesOficiales.insertarAlumno(postulacion.getEstudiante());
            cupoActual++;
            postulacion.setEstado("ACEPTADO");
            
            if (cupoActual == cupoMaximo) {
                estado = "COMPLETO";
            }
        } else {
            listaEspera.encolarAlumno(postulacion.getEstudiante());
            postulacion.setEstado("EN ESPERA");
        }
    }
    
    public void mostrarDatos() {
        System.out.println("=== DATOS DEL CURSO ===");
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombreCurso);
        System.out.println("Deporte: " + (deporte != null ? deporte.getNombre() : "No asignado"));
        System.out.println("Instructor: " + (instructor != null ? instructor.getNombre() + " " + instructor.getApellidos() : "No asignado"));
        System.out.println("Ubicación: " + ubicacion);
        System.out.println("Horario: " + horario);
        System.out.println("Cupo: " + cupoActual + "/" + cupoMaximo);
        System.out.println("Estado: " + estado);
        System.out.println("Fecha Creación: " + fechaCreacion);
        System.out.println("Estudiantes oficiales: " + estudiantesOficiales.getTamaño());
        System.out.println("En lista de espera: " + listaEspera.getTamaño());
    }
    
    public void mostrarEstudiantes() {
        System.out.println("=== ESTUDIANTES DEL CURSO " + nombreCurso + " ===");
        estudiantesOficiales.mostrarAlumnos();
    }
}