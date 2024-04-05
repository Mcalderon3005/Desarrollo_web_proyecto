/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.service.impl;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.prototipo.service.FirebaseCloudMessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tayron
 */
@Service
public class FirebaseCloudMessagingServiceImpl implements FirebaseCloudMessagingService {

    @Override
    public void enviarNotificacion(String tokenDispositivo, String titulo, String cuerpoMensaje) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    @Autowired
//    private FirebaseMessaging firebaseMessaging;
//
//    @Override
//    public void enviarNotificacion(String tokenDispositivo, String titulo, String cuerpoMensaje) {
//        Message message = Message.builder()
//                .setToken(tokenDispositivo)
//                .putData("titulo", titulo)
//                .putData("mensaje", cuerpoMensaje)
//                .build();
//
//        try {
//            firebaseMessaging.send(message);
//        } catch (FirebaseMessagingException e) {
//            // Manejar cualquier excepción que pueda ocurrir al enviar la notificación
//            e.printStackTrace();
//        }
//    }

    // Otros métodos de la interfaz pueden implementarse de manera similar
}
