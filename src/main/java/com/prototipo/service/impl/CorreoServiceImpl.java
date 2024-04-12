/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.service.impl;


import com.prototipo.service.CorreoService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tayron
 */
@Service
public class CorreoServiceImpl implements CorreoService{

    @Autowired
    private JavaMailSender mailSender;
    
    @Override
    public void enviarCorreoHtml(
            String para,
            String asunto,
            String contenidoHtml)
            throws MessagingException {
        MimeMessage correo = mailSender.createMimeMessage();
        //Se necesita un apoyo para el correo
        MimeMessageHelper apoyo = new MimeMessageHelper(correo, true);
        //Se puede mandar un archivo adjunto, muchos metodos de apoyo,**Proyecto noti**
        apoyo.setTo(para);  //Para
        apoyo.setSubject(asunto);  //El asunto 
        apoyo.setText(contenidoHtml, true);  //Contenido
        //Se envia el correo
        mailSender.send(correo);
        
    }
    
}

