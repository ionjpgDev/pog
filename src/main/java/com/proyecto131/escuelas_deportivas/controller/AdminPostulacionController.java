package com.proyecto131.escuelas_deportivas.controller;

import com.proyecto131.escuelas_deportivas.model.Postulacion;
import com.proyecto131.escuelas_deportivas.service.ServicioPostulaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/postulaciones")
public class AdminPostulacionController {

    @Autowired
    private ServicioPostulaciones servicioPostulaciones;

    @GetMapping
    public String listarPostulaciones(Model model) {
        List<Postulacion> postulaciones = servicioPostulaciones.obtenerPendientesComoLista();
        model.addAttribute("titulo", "Postulaciones Pendientes");
        model.addAttribute("postulaciones", postulaciones);
        return "admin/postulaciones";
    }

    @PostMapping("/aceptar/{id}")
    public String aceptarPostulacion(@PathVariable String id, RedirectAttributes redirectAttributes) {
        servicioPostulaciones.aceptar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Postulación aceptada");
        return "redirect:/admin/postulaciones";
    }

    @PostMapping("/rechazar/{id}")
    public String rechazarPostulacion(@PathVariable String id, RedirectAttributes redirectAttributes) {
        servicioPostulaciones.rechazar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Postulación rechazada");
        return "redirect:/admin/postulaciones";
    }
}
