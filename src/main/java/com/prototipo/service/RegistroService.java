/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prototipo.service;

/**
 *
 * @author Tayron
 */
import com.prototipo.domain.Usuario;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface RegistroService {

//Activar usuario, enviar el mensaje de activacion
    public Model activar(Model model, String usuario, String clave);

//crear un usuario y enviar
    public Model crearUsuario(Model model, Usuario usuario) throws MessagingException;

    public void activar(Usuario usuario, MultipartFile imagenFile);

    //Como ingresar
    public Model recordarUsuario(Model model, Usuario usuario) throws MessagingException;
}
