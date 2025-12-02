package com.proyecto131.escuelas_deportivas.model;

public class Persona {
    protected String id;
    protected String ci;
    protected String nombre;
    protected String apellidos;
    protected String email;
    protected String telefono;
    protected String fechaNacimiento;
    protected String fechaRegistro;
    protected boolean activo;
    protected String contrasena;
    
    public Persona() {
        this.id = "P" + System.currentTimeMillis();
        this.fechaRegistro = java.time.LocalDate.now().toString();
        this.activo = true;
    }
    
    public Persona(String ci, String nombre, String apellidos, String email, 
                   String telefono, String fechaNacimiento, String contrasena) {
        this();
        this.ci = ci;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasena = contrasena;
    }
    
    // Getters y Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getCi() {
        return ci;
    }
    
    public void setCi(String ci) {
        this.ci = ci;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public boolean isActivo() {
        return activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public void mostrarDatos() {
        System.out.println("=== DATOS DE PERSONA ===");
        System.out.println("ID: " + id);
        System.out.println("CI: " + ci);
        System.out.println("Nombre: " + nombre + " " + apellidos);
        System.out.println("Email: " + email);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Fecha Nacimiento: " + fechaNacimiento);
        System.out.println("Fecha Registro: " + fechaRegistro);
        System.out.println("Activo: " + (activo ? "Sí" : "No"));
    }
}