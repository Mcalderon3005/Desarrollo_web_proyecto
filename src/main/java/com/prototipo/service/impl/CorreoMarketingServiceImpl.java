/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.service.impl;

import com.prototipo.domain.Usuario;
import com.prototipo.domain.Vehiculo;
import com.prototipo.service.CorreoMarketingService;
import com.prototipo.service.CorreoService;
import com.prototipo.service.UsuarioDetailsService;
import com.prototipo.service.VehiculoService;
import jakarta.mail.MessagingException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Tayron
 */
@Service
public class CorreoMarketingServiceImpl implements CorreoMarketingService {

    @Autowired
    private MessageSource messageSource;

//    @Autowired
//    private VehiculoService vehiculoService;
    
    @Autowired
    private CorreoService correoService;
    
//    @Autowired
//    private UsuarioDetailsService usuarioDetailsService;
    
//    @Scheduled(fixedRate = 60000)
    @Override
    public void mostrarMensajeLanzamientoVehiculo(Usuario usuario, String mensaje) throws MessagingException {
        //var lista = vehiculoService.getVehiculos(false);
        
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        String mensaje = messageSource.getMessage("marketing.ultimoVehiculo",null, Locale.getDefault());
//        mensaje = String.format(mensaje, lista.get(0).getMarca(), lista.get(0).getModelo());
        mensaje = "Hola " + usuario.getNombre() + " "+ usuario.getApellidos() + " " + mensaje;
        String asunto = messageSource.getMessage("marketing.mensaje.vehiculo",null, Locale.getDefault());
        correoService.enviarCorreoHtml(usuario.getCorreo(), asunto, mensaje);
    }

}
