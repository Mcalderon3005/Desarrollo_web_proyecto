/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.service.impl;

import com.prototipo.dao.TarjetaDao;
import com.prototipo.dao.UsuarioDao;
import com.prototipo.domain.Tarjeta;
import com.prototipo.domain.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.prototipo.service.TarjetaService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

/**
 *
 * @author Tayron
 */
@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaDao tarjetaDao;
//    @Autowired
//    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public Tarjeta getTarjeta(Tarjeta tarjeta) {
        return tarjetaDao.findByNumeroTarjeta(tarjeta.getNumeroTarjeta());
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public Tarjeta getTarjetaPorUsuarioAutenticado() {
        String username;
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        if (username.isBlank()) {
            System.out.println("username en blanco");
            return null;
        }

        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            System.out.println("usuario no encontrado " + username);
            return null;
        }
        
        return tarjetaDao.findByIdUsuario(usuario.getIdUsuario());
//return null;
    }
    

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional
    public void save(Tarjeta tarjeta) {
        String username;
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        if (username.isBlank()) {
            System.out.println("username en blanco");
            return;
        }

        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            System.out.println("usuario no encontrado " + username);
            return;
        }

        tarjeta.setUsuario(usuario);
        tarjetaDao.save(tarjeta);
    }

    @Override
    @Transactional
    public void delete(Tarjeta tarjeta) {
        tarjetaDao.delete(tarjeta);
    }

    @Override
    public void update(Tarjeta tarjeta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
