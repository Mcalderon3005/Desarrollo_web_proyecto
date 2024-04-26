/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.prototipo.controller;

import com.prototipo.domain.Item;
import com.prototipo.domain.Vehiculo;
import com.prototipo.service.ItemService;
import com.prototipo.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Tayron
 */
@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/carrito/agregar/{idVehiculo}")
    //ModelAndView porque solo se va a refrescar un pedazo
    public ModelAndView agregar(Model model, Item item) {
        Item item2 = itemService.get(item);
        if (item2 == null) {  //Lo buscamos en el array 
            Vehiculo v = vehiculoService.getVehiculo(item);
            item2 = new Item(v); //En item2 esta la info, constructor
        }
        //Modificado, para que solo se agreguen vehiculo o accesorios cuando hay mayores que 0
        if (item2.getExistencias() > 0) {
            itemService.save(item2);
            var lista = itemService.gets();
            var totalCarrito = 0;
            var carritoSubTotalVenta = 0;
            for (Item i : lista) {
                totalCarrito += i.getCantidad();
                carritoSubTotalVenta += (i.getCantidad() * i.getPrecio());
            }
            model.addAttribute("listaItems", lista);
            model.addAttribute("listaTotal", totalCarrito);
            model.addAttribute("carritoSubTotal", carritoSubTotalVenta);
        }

        return new ModelAndView("/carrito/fragmentos :: verCarrito2");
    }

    @GetMapping("/carrito/listado")
    public String listado(Model model) {
        var lista = itemService.gets();
        var totalCarrito = 0;
        var carritoSubTotalVenta = 0;
        double descuento = 0;
        double carritoTotal = 0;
        double subtotalLote = 0;
        for (Item i : lista) {
            totalCarrito += i.getCantidad();
            subtotalLote = (i.getCantidad() * i.getPrecio());
            carritoSubTotalVenta += subtotalLote;
            if (i.getCantidad() >= 2) {
                descuento += subtotalLote * 0.05;
            }
        }
        carritoTotal = carritoSubTotalVenta - descuento;

        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarrito);
        model.addAttribute("carritoSubTotal", carritoSubTotalVenta);
        model.addAttribute("descuento", descuento);
        model.addAttribute("carritoTotal", carritoTotal);

        return "/carrito/listado";
    }

    @GetMapping("/carrito/eliminar/{idVehiculo}")
    public String eliminar(Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }

    @GetMapping("/carrito/modificar/{idVehiculo}")
    public String modificar(Item item, Model model) {
        item = itemService.get(item);
        model.addAttribute("item", item);
        return "/carrito/modifica";
    }

    @PostMapping("/carrito/guardar")
    public String guardar(Item item) {
        itemService.update(item);
        return "redirect:/carrito/listado";
    }

    @GetMapping("/facturar/carrito")
    public String facturar() {
        var lista = itemService.gets();
        if (lista != null && !lista.isEmpty()) {
            itemService.facturar();
        } else {
            System.out.println("No hay items en el carrito");
        }
        return "redirect:/";
    }
}