/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.controller;

import com.prototipo.domain.Categoria;
import com.prototipo.service.CategoriaService;
import com.prototipo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Tayron
 */
@Controller
//@RequestMapping("/")
public class HomeController {

    @Autowired
    private VehiculoService vehiculoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = {"/", "/index"})
    public String listado(Model model) {
        var vehiculos = vehiculoService.getVehiculos(false);
        model.addAttribute("vehiculos", vehiculos);
        return "index";
    }
    
    @GetMapping(value = {"/{idCategoria}", "/index/{idCategoria}"})
    public String modifica(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categoria", categoria);
        return "index";
    }

//    @GetMapping("/index/{idCategoria}")
//    public String modifica(Categoria categoria, Model model) {
//        //Se obtiene un objeto categoria
//        categoria = categoriaService.getCategoria(categoria);
//
//        //Fundamental, del @OneToMany
//        var lista = categoria.getVehiculo();
//        model.addAttribute("vehiculos", lista);
//
//        //Para que no se quiten las categorias
//        //Se vuelven a inyectar
//        var categorias = categoriaService.getCategorias(false);
//        model.addAttribute("categorias", categorias);
//
//        return "index";
//    }

//    @GetMapping("/index/activos")
//    public String listadoActivos(Model model) {
//        var vehiculosActivos = vehiculoService.getVehiculos(true); // Obtener vehículos activos
//        model.addAttribute("vehiculos", vehiculosActivos); // Pasar vehículos activos al modelo
//        return "index"; // Devolver la misma vista que la de vehículos inactivos
//    }
//
//    @GetMapping("/index/inactivos")
//    public String listadoInactivos(Model model) {
//        var vehiculosInactivos = vehiculoService.getVehiculos(false); // Obtener vehículos inactivos
//        model.addAttribute("vehiculos", vehiculosInactivos); // Pasar vehículos inactivos al modelo
//        return "index"; // Devolver la misma vista que la de vehículos activos
//    }
    @GetMapping("/encuentranos/listado")
    public String encuentranos() {
        return "encuentranos/listado";
    }

    @GetMapping("/promociones/listado")
    public String promociones() {
        return "promociones/listado";
    }

    @GetMapping("/envios/listado")
    public String envios() {
        return "envios/listado";
    }

//@GetMapping("/listado/{idCategoria}")
//    public String modifica(Vehiculo vehiculo, Model model) {
//        //Se obtiene un objeto categoria
//        vehiculo = vehiculoService.getVehiculo(vehiculo);
//        
//        //Fundamental, del @OneToMany
//        var lista = vehiculo.getVehiculo();
//        model.addAttribute("productos", lista);
//        
//        //Para que no se quiten las categorias
//        //Se vuelven a inyectar
//        var categorias = categoriaService.getCategorias(false);
//        model.addAttribute("categorias", categorias);
//        
//        return "/pruebas/listado";
//    }
}
