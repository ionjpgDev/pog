package com.proyecto131.escuelas_deportivas.service;

import com.proyecto131.escuelas_deportivas.model.Deporte;
import com.proyecto131.escuelas_deportivas.util.estructuras.ListaEDDeporte;
import org.springframework.stereotype.Service;

@Service
public class ServicioDeportes {
    private ListaEDDeporte deportes;
    
    public ServicioDeportes() {
        this.deportes = new ListaEDDeporte();
        cargarDatosEjemplo();
    }
    
    private void cargarDatosEjemplo() {
        deportes.insertarDeporte(new Deporte("Fútbol", "Equipo", 6, 18, false));
        deportes.insertarDeporte(new Deporte("Baloncesto", "Equipo", 8, 20, false));
        deportes.insertarDeporte(new Deporte("Natación", "Individual", 5, 25, true));
        deportes.insertarDeporte(new Deporte("Tenis", "Individual", 7, 30, false));
        deportes.insertarDeporte(new Deporte("Voleibol", "Equipo", 10, 22, false));
    }
    
    public ListaEDDeporte obtenerTodosDeportes() {
        return deportes;
    }
    
    public Deporte buscarDeportePorId(String id) {
        return deportes.buscarDeporte(id);
    }
    
    public Deporte buscarDeportePorNombre(String nombre) {
        return deportes.buscarPorNombre(nombre);
    }
    
    public void agregarDeporte(Deporte deporte) {
        deportes.insertarDeporte(deporte);
    }
    
    public boolean eliminarDeporte(String id) {
        return deportes.eliminarDeporte(id) != null;
    }
    
    public int cantidadDeportes() {
        return deportes.getTamaño();
    }
}