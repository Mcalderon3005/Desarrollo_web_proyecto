/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.controller;

import com.prototipo.domain.Usuario;
import com.prototipo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Tayron
 */
@Controller
@RequestMapping("/perfil")
public class PerfilController {
    
    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("/mostrar")
    public String perfil(Model model) {
//        usuario = usuarioService.getUsuario(usuario);
//        model.addAttribute("usuario", usuario);
        model = usuarioService.mostrarInfo(model);
        return "perfil/listado";
    }
}
