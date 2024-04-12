/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.controller;

import com.prototipo.service.VehiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.Cookie;


/**
 *
 * @author Tayron
 */
@Controller
public class MonedaController {
    
    @Autowired
    VehiculoService vehiculoService;
    
//    @GetMapping("/cambiar-moneda")
//    public String cambiarMoneda(@RequestParam String moneda, Model model, HttpServletResponse response, HttpServletRequest request) {
//        // Agregar la cookie de moneda seleccionada antes de obtener los precios
//        Cookie cookie = new Cookie("moneda", moneda);
//        cookie.setMaxAge(30 * 24 * 60 * 60); // Duración de la cookie en segundos (30 días)
//        cookie.setPath("/"); // Ruta válida para toda la aplicación
//        response.addCookie(cookie);
//        
//        // Obtener los precios actualizados con la moneda seleccionada
//        model = vehiculoService.getPrecios(model, moneda, request);
//        
//        return "redirect:/";
//    }
//
//@GetMapping("/cambiar-moneda")
////    @GetMapping("/")
////    public String home(@RequestParam(name = "moneda", required = false, defaultValue = "dolar") String moneda, Model model) {
//public String cambiarMoneda(@RequestParam(name = "moneda", required = false, defaultValue = "dolar") String moneda, Model model, HttpSession session) {
//    model = vehiculoService.getPrecios(model, moneda);
//    session.setAttribute("moneda", moneda);
//    return "redirect:/";
//}
   
//    @GetMapping("/cambiar-moneda")
//    public String cambiarMoneda(@RequestParam(name = "moneda") String nuevaMoneda, HttpSession session) {
//        session.setAttribute("moneda", nuevaMoneda);
//        return "redirect:/";
//    }

}
