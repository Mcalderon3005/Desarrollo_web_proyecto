/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.service.impl;

import com.prototipo.domain.Vehiculo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.prototipo.dao.VehiculoDAO;
import com.prototipo.service.VehiculoService;
//import java.util.Locale;
//import java.util.Optional;
import org.springframework.ui.Model;

//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.Cookie;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;


/**
 *
 * @author Tayron
 */
@Service
public class VehiculoServiceImpl implements VehiculoService{
    
    @Autowired
    private VehiculoDAO autoDao;
    
//    @Autowired
//    private VehiculoService vehiculoService;
//    
//    @Autowired
//    private MessageSource messageSource;

    @Override
    @Transactional(readOnly=true)
    public List<Vehiculo> getVehiculos(boolean activos) {
        var lista=autoDao.findAll();
        if (activos){ //Si se quieren solo las autos activas
            lista.removeIf(c -> !c.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Vehiculo getVehiculo(Vehiculo auto) {
        return autoDao.findById(auto.getIdVehiculo()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Vehiculo auto) {
        autoDao.save(auto);
    }

    @Override
    @Transactional
    public void delete(Vehiculo auto) {
        autoDao.delete(auto);
    }
    
    //CON COOKIES
//    @Override
//    public Model getPrecios(Model model, String moneda, HttpServletRequest request) {
//        List<Vehiculo> vehiculos = autoDao.findAll();
//
//        // Obtener la moneda seleccionada de la cookie
//        Cookie[] cookies = request.getCookies();
//        String monedaSeleccionada = moneda; // Valor predeterminado si no se encuentra la cookie
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("moneda")) {
//                    monedaSeleccionada = cookie.getValue();
//                    break;
//                }
//            }
//        }
//
//        // Realizar la conversión de moneda para cada vehículo
//        for (Vehiculo vehiculo : vehiculos) {
//            double precioConvertido = convertirMoneda(vehiculo.getPrecio(), monedaSeleccionada);
//            vehiculo.setPrecio(precioConvertido);
//        }
//
//        model.addAttribute("vehiculos", vehiculos);
//        model.addAttribute("moneda", monedaSeleccionada);
//        return model;
//    }
//    
//    
//    // Método para convertir el precio a la moneda seleccionada
//    private double convertirMoneda(double precio, String moneda) {
//        if (moneda.equals("euro")) {
//            // Convertir de euros a dólares
//            return precio * 0.92;
//        } else {
//            // Retornar el precio original
//            return precio;
//        }
//    }

    
    @Override
    public Model getPrecios(Model model, String moneda) {
        List<Vehiculo> vehiculos = autoDao.findAll();
        
        // Realizar la conversión de moneda para cada vehículo
        for (Vehiculo vehiculo : vehiculos) {
            double precioConvertido = convertirMoneda(vehiculo.getPrecio(), moneda);
            vehiculo.setPrecio(precioConvertido);
        }
//        System.out.println(moneda);
        model.addAttribute("vehiculos", vehiculos);
        model.addAttribute("moneda", moneda);
        return model;
    }
    
    // Método para convertir el precio a la moneda seleccionada
    private double convertirMoneda(double precio, String moneda) {
    if (moneda.equals("euro")) {
        // Convertir de euros a dólares
        return precio * 0.92;
    } else {
        //retornar el precio original
        return precio;
    }
    }
    
    
    @Override
    public Model getPreciosIva(Model model, String pais, Vehiculo vehiculo) {
    vehiculo = autoDao.findById(vehiculo.getIdVehiculo()).orElse(null);
    
    double iva = calIva(vehiculo.getPrecio(), pais);
    double precioFinal = vehiculo.getPrecio() + iva;

    model.addAttribute("vehiculo", vehiculo);
    model.addAttribute("iva", iva);
    model.addAttribute("precioFinal", precioFinal);
    
    return model;
}
    
    private double calIva(double precio, String pais){
        if("usa".equals(pais)){
//            if(precio >= 11000){
//                return precio * 0.12;
//            }else{
                return precio * 0.21;
//            }
        }else if("espana".equals(pais)){
//            if(precio <= 15000){
                return precio * 0.8;
//            }
        }
        
        return precio;
    }
}


   

//    @Override
//    public double convertirMoneda(double valorOriginal, String moneda) {
//        double tasaCambio = 0.85; // Obtiene la tasa de cambio actual
//        if ("EUR".equals(moneda)) {
//            return valorOriginal * tasaCambio;
//        } else {
//            return valorOriginal; // No es necesario convertir si es la misma moneda
//        }
//    }
    
    
//    @Override
//    public Model getPrecios(Model model,String moneda) {
//        //Vehiculo vehiculo = vehiculoService.findByPrecio(model,moneda);
//        
//        String precioDolar = messageSource.getMessage("precio.dolar",null, Locale.getDefault());
//        String precioEuro = messageSource.getMessage("precio.euro", null, Locale.getDefault());
//        
//        model.addAttribute("precioDolar", precioDolar);
//        model.addAttribute("precioEuro", precioEuro);
//        
//        
//        return model;
//    }
    
    
   
    
//   @Override
//public double getPrecio(Vehiculo auto){
//    Optional<Vehiculo> optionalVehiculo = autoDao.findById(auto.getIdVehiculo()); // Obtener el vehículo por ID
//    
//    // Verificar si el vehículo existe en el Optional
//    if (optionalVehiculo.isPresent()) {
//        Vehiculo vehiculo = optionalVehiculo.get(); // Obtener el vehículo del Optional
//        return vehiculo.getPrecio(); // Devolver el precio del vehículo
//    } else {
//        return 0.0; // O algún otro valor predeterminado si el vehículo no se encuentra
//    }
//}


    

