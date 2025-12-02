package com.proyecto131.escuelas_deportivas.model;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDCurso;
import java.util.Scanner;
public class Deporte {
    private String id;
    private String nombre;
    private String tipo;
    private int edadMinima;
    private int edadMaxima;
    private boolean requiereCertificado;
    private boolean activo;
    private ListaEDCurso cursos;

    public Deporte(String nombre, String tipo, int edadMinima, int edadMaxima, boolean requiereCertificado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
        this.requiereCertificado = requiereCertificado;
        this.activo = true;
        this.id = "D" + System.currentTimeMillis();
        this.cursos = new ListaEDCurso();
    }

    public Deporte() {
        Scanner leer = new Scanner(System.in);
        System.out.println("REGISTRO DE NUEVO DEPORTE");

        System.out.print("Nombre: ");
        this.nombre = leer.nextLine();

        System.out.print("Tipo: ");
        this.tipo = leer.nextLine();

        System.out.print("Edad Minima: ");
        this.edadMinima = Integer.parseInt(leer.nextLine());

        System.out.print("Edad Maxima: ");
        this.edadMaxima = Integer.parseInt(leer.nextLine());

        System.out.print("Requiere Certificado (true/false): ");
        this.requiereCertificado = Boolean.parseBoolean(leer.nextLine());

        this.activo = true;
        this.id = "D" + System.currentTimeMillis();
        this.cursos = new ListaEDCurso();

        System.out.println("Deporte registrado");
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ListaEDCurso getCursos() {
        return cursos;
    }

    public void mostrarDatos() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Tipo: " + tipo);
        System.out.println("Edad Minima: " + edadMinima);
        System.out.println("Edad Maxima: " + edadMaxima);
        System.out.println("Requiere Certificado: " + requiereCertificado);
        System.out.println("Activo: " + activo);
        System.out.println("Cursos: " + cursos.getTamaño());
    }
}
