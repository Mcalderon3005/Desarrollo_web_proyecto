/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prototipo.service;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tayron
 */
@Service
public interface FirebaseCloudMessagingService {

    // Método para enviar una notificación push a un único dispositivo
    void enviarNotificacion(String tokenDispositivo, String titulo, String cuerpoMensaje);

    // Método para enviar una notificación push a varios dispositivos
//    void enviarNotificacionMulticast(List<String> tokensDispositivos, String titulo, String cuerpoMensaje);
//
//    // Método para enviar una notificación push a un tópico específico
//    void enviarNotificacionATopico(String nombreTopico, String titulo, String cuerpoMensaje);

}
