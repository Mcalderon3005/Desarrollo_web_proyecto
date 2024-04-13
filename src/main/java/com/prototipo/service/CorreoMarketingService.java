/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prototipo.service;

import com.prototipo.domain.Usuario;
import jakarta.mail.MessagingException;

/**
 *
 * @author Tayron
 */
public interface CorreoMarketingService {
    
    public void mostrarMensajeLanzamientoVehiculo(Usuario usuario, String mensaje)throws MessagingException;
}
