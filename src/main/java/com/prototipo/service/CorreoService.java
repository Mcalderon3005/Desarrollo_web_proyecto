/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prototipo.service;

import jakarta.mail.MessagingException;

/**
 *
 * @author Tayron
 */
public interface CorreoService {

    //La firma del metodo para enviar correos html, varias formas de texto
    public void enviarCorreoHtml(
            String para,
            String asunto,
            String contenidoHtml)
            throws MessagingException;
}
