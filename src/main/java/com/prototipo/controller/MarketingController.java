/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.controller;

import com.prototipo.domain.Usuario;
import com.prototipo.service.CorreoMarketingService;
import com.prototipo.service.RegistroService;
import com.prototipo.service.UsuarioService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Tayron
 */
@Controller
@RequestMapping("/marketing")
public class MarketingController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CorreoMarketingService correoMarketingService;

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        var usuariolista = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuariolista);
        
        return "/marketing/listado";
    }

    @PostMapping("/enviarMensaje")
    public String enviarMensaje(Usuario usuario, @RequestParam("mensaje") String mensaje) throws MessagingException {
        usuario = usuarioService.getUsuario(usuario);
        correoMarketingService.mostrarMensajeLanzamientoVehiculo(usuario, mensaje);
        return "redirect:/marketing/nuevo";
    }

}
