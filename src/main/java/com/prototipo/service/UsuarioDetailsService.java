/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.prototipo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author Tayron
 */
public interface UsuarioDetailsService {
    
    //Devolver las caracteristicas del usuario
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException;
}
